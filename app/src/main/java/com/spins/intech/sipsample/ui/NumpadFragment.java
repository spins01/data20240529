package com.spins.intech.sipsample.ui;

import com.portsip.PortSipEnumDefine;
import com.portsip.PortSipSdk;
import com.spins.intech.MyApplication;
import com.spins.intech.R;
import com.spins.intech.sipsample.adapter.AudioDeviceAdapter;
import com.spins.intech.sipsample.receiver.PortMessageReceiver;
import com.spins.intech.sipsample.service.PortSipService;
import com.spins.intech.sipsample.util.CallManager;
import com.spins.intech.sipsample.util.Ring;
import com.spins.intech.sipsample.util.Session;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;

public class NumpadFragment extends BaseFragment implements AdapterView.OnItemSelectedListener, View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, PortMessageReceiver.BroadcastListener {
    private EditText etSipNum;
    private TextView tvTips;
    private Spinner spLine;
    CheckBox cbSendVideo, cbRecvVideo, cbConference, cbSendSdp;
    MyApplication application;
    MainActivity activity;
    AudioDeviceAdapter audioDeviceAdapter;

    final PortSipEnumDefine.AudioDevice[] audioDevices = new PortSipEnumDefine.AudioDevice[]{PortSipEnumDefine.AudioDevice.EARPIECE
            , PortSipEnumDefine.AudioDevice.SPEAKER_PHONE
            , PortSipEnumDefine.AudioDevice.BLUETOOTH,
            PortSipEnumDefine.AudioDevice.WIRED_HEADSET};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        application = (MyApplication) activity.getApplicationContext();
        return inflater.inflate(R.layout.numpad, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSipNum = view.findViewById(R.id.etsipaddress);
        cbSendSdp = view.findViewById(R.id.sendSdp);
        cbConference = view.findViewById(R.id.conference);
        cbSendVideo = view.findViewById(R.id.sendVideo);
        cbRecvVideo = view.findViewById(R.id.acceptVideo);

        spLine = view.findViewById(R.id.sp_lines);

        TableLayout dtmfPad = view.findViewById(R.id.dtmf_pad);
        TableLayout functionPad = view.findViewById(R.id.function_pad);


        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.lines, android.R.layout.simple_list_item_1);
        spLine.setAdapter(spinnerAdapter);
        spLine.setSelection(CallManager.Instance().CurrentLine);
        spLine.setOnItemSelectedListener(this);
        audioDeviceAdapter = new AudioDeviceAdapter(audioDevices);

        view.findViewById(R.id.dial).setOnClickListener(this);
        view.findViewById(R.id.pad).setOnClickListener(this);
        view.findViewById(R.id.delete).setOnClickListener(this);
        tvTips = (TextView) view.findViewById(R.id.txtips);

        cbConference.setChecked(application.getMConference());
        cbConference.setOnCheckedChangeListener(this);
        SetTableItemClickListener(functionPad);
        SetTableItemClickListener(dtmfPad);
        onHiddenChanged(false);
    }

    public void onBroadcastReceiver(Intent intent) {
        String action = intent == null ? "" : intent.getAction();
        if (PortSipService.CALL_CHANGE_ACTION.equals(action)) {
            long sessionId = intent.getLongExtra(PortSipService.EXTRA_CALL_SEESIONID, Session.INVALID_SESSION_ID);
            String status = intent.getStringExtra(PortSipService.EXTRA_CALL_DESCRIPTION);
            showTips(status);
        } else if (PortSipService.ACTION_SIP_AUDIODEVICE.equals(action)) {
            PortSipEnumDefine.AudioDevice device = CallManager.Instance().getCurrentAudioDevice();
            audioDeviceAdapter.setSelectalbeAudioDevice(CallManager.Instance().getSelectableAudioDevice());
            audioDeviceAdapter.notifyDataSetChanged();
            ((Button) getView().findViewById(R.id.audio_device)).setText(device.toString());
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            ShowCurrentLineState();
            activity.receiver.broadcastReceiver = this;
            audioDeviceAdapter.setSelectalbeAudioDevice(CallManager.Instance().getSelectableAudioDevice());
        }
    }

    private void ShowCurrentLineState() {
        Session current = CallManager.Instance().getCurrentSession();
        switch (current.state) {
            case CLOSED:
            case FAILED:
                showTips(current.lineName + ": Idle");
                break;
            case CONNECTED:
                showTips(current.lineName + ": CONNECTED");
                break;
            case INCOMING:
                showTips(current.lineName + ": INCOMING");
                break;
            case TRYING:
                showTips(current.lineName + ": TRYING");
                break;
        }
        Button mute = getView().findViewById(R.id.mute);
        if (current.bMute) {
            mute.setText("Mute");
        } else {
            mute.setText("UnMute");
        }
    }

    private void SetTableItemClickListener(TableLayout table) {

        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow tableRow = (TableRow) table.getChildAt(i);
            int line = tableRow.getChildCount();
            for (int index = 0; index < line; index++) {
                tableRow.getChildAt(index).setOnClickListener(this);
            }
        }
    }

    private void showTips(String text) {
        tvTips.setText(text);
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    private void SwitchPanel() {
        View view = getView().findViewById(R.id.function_pad);
        View dtmfView = getView().findViewById(R.id.dtmf_pad);
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
            dtmfView.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);
            dtmfView.setVisibility(View.INVISIBLE);
        }
    }


    AlertDialog TransferDLG;
    AlertDialog AttendTransferDLG;

    private void showTransferDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());
        View textEntryView = factory.inflate(R.layout.transferdialog, null);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("Transfer input");
        builder.setView(textEntryView);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                handleTransfer();
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                handleNegativeButtonClick();
            }
        });
        TransferDLG = builder.create();
        TransferDLG.show();
    }

    private void showAttendTransferDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater factory = LayoutInflater.from(getActivity());
        View textEntryView = factory.inflate(R.layout.attendtransferdialog, null);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("Transfer input");
        builder.setView(textEntryView);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                handleAttendTransfer();
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                handleNegativeButtonClick();
            }
        });
        AttendTransferDLG = builder.create();
        AttendTransferDLG.show();
    }

    private void handleNegativeButtonClick() {
        AttendTransferDLG = null;
        TransferDLG = null;
    }

    private void handleTransfer() {
        Session currentLine = CallManager.Instance().getCurrentSession();
        if (currentLine.state != Session.CALL_STATE_FLAG.CONNECTED) {
            showTips("current line must be connected ");
            return;
        }
        EditText transferTo = TransferDLG.findViewById(R.id.ettransferto);
        String referTo = transferTo.getText().toString();
        if (TextUtils.isEmpty(referTo)) {
            showTips("The transfer number is empty");
            return;
        }
        int rt = application.getMEngine().refer(currentLine.sessionID, referTo);
        if (rt != 0) {
            showTips(currentLine.lineName + ": failed to transfer");
        } else {
            showTips(currentLine.lineName + " success transfered");
        }
        TransferDLG = null;

    }

    private void handleAttendTransfer() {
        Session currentLine = CallManager.Instance().getCurrentSession();
        if (currentLine.state != Session.CALL_STATE_FLAG.CONNECTED) {
            showTips("current line must be connected ");
            return;
        }
        EditText transferTo = AttendTransferDLG.findViewById(R.id.ettransferto);
        EditText transferLine = AttendTransferDLG.findViewById(R.id.ettransferline);
        String referTo = transferTo.getText().toString();
        if (TextUtils.isEmpty(referTo)) {
            showTips("The transfer number is empty");
            return;
        }
        String lineString = transferLine.getText().toString();

        int line = Integer.parseInt(lineString);


        if (line < 0 || line >= CallManager.MAX_LINES) {
            showTips("The replace line out of range");
            return;
        }
        Session replaceSession = CallManager.Instance().findSessionByIndex(line);
        if (replaceSession.state != Session.CALL_STATE_FLAG.CONNECTED) {
            showTips("The replace line does not established yet");
            return;
        }

        if (replaceSession.sessionID == currentLine.sessionID) {
            showTips("The replace line can not be current line");
            return;
        }

        int rt = application.getMEngine().attendedRefer(currentLine.sessionID, replaceSession.sessionID, referTo);

        if (rt != 0) {
            showTips(currentLine.lineName + ": failed to Attend transfer");
        } else {
            showTips(currentLine.lineName + ": Transferring");
        }
        AttendTransferDLG = null;
    }

    @Override
    public void onClick(View view) {

        if (application.getMEngine() == null)
            return;
        PortSipSdk portSipSdk = application.getMEngine();
        Session currentLine = CallManager.Instance().getCurrentSession();

        if (view.getId() == R.id.zero || view.getId() == R.id.one
                || view.getId() == R.id.two || view.getId() == R.id.three || view.getId() == R.id.four
                || view.getId() == R.id.five || view.getId() == R.id.six || view.getId() == R.id.seven || view.getId() == R.id.eight || view.getId() == R.id.nine
                || view.getId() == R.id.star || view.getId() == R.id.sharp) {

            String numberString = ((Button) view).getText().toString();

            char number = numberString.charAt(0);
            etSipNum.append(numberString);
            if (CallManager.Instance().isRegistered && currentLine.state == Session.CALL_STATE_FLAG.CONNECTED) {
                if (number == '*') {
                    portSipSdk.sendDtmf(currentLine.sessionID, PortSipEnumDefine.ENUM_DTMF_MOTHOD_RFC2833, 10,
                            160, true);
                    return;
                }
                if (number == '#') {
                    portSipSdk.sendDtmf(currentLine.sessionID, PortSipEnumDefine.ENUM_DTMF_MOTHOD_RFC2833, 11,
                            160, true);
                    return;
                }
                int sum = Integer.parseInt(numberString);// 0~9
                portSipSdk.sendDtmf(currentLine.sessionID, PortSipEnumDefine.ENUM_DTMF_MOTHOD_RFC2833, sum,
                        160, true);
            }
        } else if (view.getId() == R.id.delete) {
            int cursorpos = etSipNum.getSelectionStart();
            if (cursorpos - 1 >= 0) {
                etSipNum.getText().delete(cursorpos - 1, cursorpos);
            }
        } else if (view.getId() == R.id.pad) {
            SwitchPanel();
        } else if (view.getId() == R.id.dial) {


            String callTo = etSipNum.getText().toString();
            if (callTo.length() <= 0) {
                showTips("The phone number is empty.");
                return;
            }
            if (!currentLine.IsIdle()) {
                showTips("Current line is busy now, please switch a line.");
                return;
            }

            // Ensure that we have been added one audio codec at least
            if (portSipSdk.isAudioCodecEmpty()) {
                showTips("Audio Codec Empty,add audio codec at first");
                return;
            }

            // Usually for 3PCC need to make call without SDP
            long sessionId = portSipSdk.call(callTo, cbSendSdp.isChecked(), cbSendVideo.isChecked());
            if (sessionId <= 0) {
                showTips("Call failure");
                return;
            }
            //default send video
            portSipSdk.sendVideo(sessionId, true);

            currentLine.remote = callTo;

            currentLine.sessionID = sessionId;
            currentLine.state = Session.CALL_STATE_FLAG.TRYING;
            currentLine.hasVideo = cbSendVideo.isChecked();
            showTips(currentLine.lineName + ": Calling...");
        } else if (view.getId() == R.id.hangup) {
            Ring.getInstance(getActivity()).stop();
            switch (currentLine.state) {
                case INCOMING:
                    portSipSdk.rejectCall(currentLine.sessionID, 486);
                    showTips(currentLine.lineName + ": Rejected call");
                    break;
                case CONNECTED:
                case TRYING:
                    portSipSdk.hangUp(currentLine.sessionID);

                    showTips(currentLine.lineName + ": Hang up");
                    break;
            }
            currentLine.Reset();


        } else if (view.getId() == R.id.answer) {

            if (currentLine.state != Session.CALL_STATE_FLAG.INCOMING) {
                showTips("No incoming call on current line, please switch a line.");
                return;
            }

            currentLine.state = Session.CALL_STATE_FLAG.CONNECTED;
            Ring.getInstance(getActivity()).stopRingTone();//stop ring
            int ret = portSipSdk.answerCall(currentLine.sessionID, cbRecvVideo.isChecked());//answer call
            if (ret != 0) {
                showTips("answerCall Failed! ret=" + ret);
                return;
            }
            if (application.getMConference()) {
                portSipSdk.joinToConference(currentLine.sessionID);
            }
        } else if (view.getId() == R.id.reject) {


            if (currentLine.state == Session.CALL_STATE_FLAG.INCOMING) {
                portSipSdk.rejectCall(currentLine.sessionID, 486);
                currentLine.Reset();
                Ring.getInstance(getActivity()).stop();
                showTips(currentLine.lineName + ": Rejected call");
                return;
            }

        } else if (view.getId() == R.id.hold) {

            if (!(currentLine.state == Session.CALL_STATE_FLAG.CONNECTED) || currentLine.bHold) {
                return;
            }

            int rt = portSipSdk.hold(currentLine.sessionID);
            if (rt != 0) {
                showTips("hold operation failed.");
                return;
            }
            currentLine.bHold = true;
        } else if (view.getId() == R.id.unhold) {


            if (!(currentLine.state == Session.CALL_STATE_FLAG.CONNECTED) || !currentLine.bHold) {
                return;
            }

            int rt = portSipSdk.unHold(currentLine.sessionID);
            if (rt != 0) {
                currentLine.bHold = false;
                showTips(currentLine.lineName + ": Un-Hold Failure.");
                return;
            }

            currentLine.bHold = false;
            showTips(currentLine.lineName + ": Un-Hold");
        } else if (view.getId() == R.id.attenttransfer) {

            if (!(currentLine.state == Session.CALL_STATE_FLAG.CONNECTED)) {
                showTips("Need to make the call established first");
                return;
            }
            showAttendTransferDialog();
        } else if (view.getId() == R.id.transfer) {

            if (!(currentLine.state == Session.CALL_STATE_FLAG.CONNECTED)) {
                showTips("Need to make the call established first");
                return;
            }
            showTransferDialog();
        } else if (view.getId() == R.id.mute) {

            if (currentLine.bMute) {
                portSipSdk.muteSession(currentLine.sessionID, false,
                        false, false, false);
                currentLine.bMute = false;
                ((Button) view).setText("Mute");
            } else {
                portSipSdk.muteSession(currentLine.sessionID, true,
                        true, true, true);
                currentLine.bMute = true;
                ((Button) view).setText("UnMute");
            }
        } else if (view.getId() == R.id.audio_device) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                    && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "BLUETOOTH_CONNECT permission is required for BLUETOOTH Device", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 100);
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setCancelable(true);
            audioDeviceAdapter.setSelectalbeAudioDevice(CallManager.Instance().getSelectableAudioDevice());
            audioDeviceAdapter.notifyDataSetChanged();
            builder.setAdapter(audioDeviceAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CallManager.Instance().setAudioDevice(portSipSdk, audioDevices[which]);
                }
            }).create().show();


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (CallManager.Instance().CurrentLine == position) {
            ShowCurrentLineState();
            return;
        }


        if (cbConference.isChecked()) {
            CallManager.Instance().CurrentLine = position;
            tvTips.setText("");
        } else {
            // To switch the line, must hold currently line first
            Session currentLine = CallManager.Instance().getCurrentSession();
            if (currentLine.state == Session.CALL_STATE_FLAG.CONNECTED && !currentLine.bHold) {
                application.getMEngine().hold(currentLine.sessionID);
                currentLine.bHold = true;
                showTips(currentLine.lineName + ": Hold");
            }

            CallManager.Instance().CurrentLine = position;
            currentLine = CallManager.Instance().getCurrentSession();

            // If target line was in hold state, then un-hold it
            if (currentLine.IsIdle()) {
                showTips(currentLine.lineName + ": Idle");
            } else if (currentLine.state == Session.CALL_STATE_FLAG.CONNECTED && currentLine.bHold) {
                application.getMEngine().unHold(currentLine.sessionID);
                currentLine.bHold = false;
                showTips(currentLine.lineName + ": UnHold - call established");
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton.getId() == R.id.conference) {

            if (application.getMConference() == b)
                return;
            application.setMConference(b);
            if (b) {
                application.getMEngine().createVideoConference(null, 320, 240, 0);
                CallManager.Instance().addActiveSessionToConfrence(application.getMEngine());
            } else {
                application.getMEngine().destroyConference();
            }
        }
    }
}