package com.spins.intech.sipsample.ui;


import com.spins.intech.MyApplication;
import com.spins.intech.R;
import com.spins.intech.sipsample.receiver.PortMessageReceiver;
import com.spins.intech.sipsample.service.PortSipService;
import com.spins.intech.sipsample.util.CallManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.spins.intech.sipsample.service.PortSipService.EXTRA_REGISTER_STATE;

public class LoginFragment extends BaseFragment implements AdapterView.OnItemSelectedListener, View.OnClickListener, PortMessageReceiver.BroadcastListener {
    MyApplication application;
    MainActivity activity;
    private EditText etUsername = null;
    private EditText etPassword = null;
    private EditText etSipServer = null;
    private EditText etSipServerPort = null;

    private EditText etDisplayName = null;

    private EditText etUserDomain = null;
    private EditText etAuthName = null;
    private EditText etStunServer = null;
    private EditText etStunPort = null;
    private Spinner spTransport;
    private Spinner spSRTP;
    private TextView tvStatus;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        application = (MyApplication) activity.getApplicationContext();
        View view = inflater.inflate(R.layout.login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvStatus = (TextView) view.findViewById(R.id.txtips);

        etUsername = (EditText) view.findViewById(R.id.etusername);
        etPassword = (EditText) view.findViewById(R.id.etpwd);
        etSipServer = (EditText) view.findViewById(R.id.etsipsrv);
        etSipServerPort = (EditText) view.findViewById(R.id.etsipport);

        etDisplayName = (EditText) view.findViewById(R.id.etdisplayname);
        etUserDomain = (EditText) view.findViewById(R.id.etuserdomain);
        etAuthName = (EditText) view.findViewById(R.id.etauthName);
        etStunServer = (EditText) view.findViewById(R.id.etStunServer);
        etStunPort = (EditText) view.findViewById(R.id.etStunPort);

        spTransport = (Spinner) view.findViewById(R.id.spTransport);
        spSRTP = (Spinner) view.findViewById(R.id.spSRTP);

        spSRTP.setOnItemSelectedListener(this);
        spTransport.setOnItemSelectedListener(this);

        spTransport.setAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.transports, android.R.layout.simple_list_item_1));
        spSRTP.setAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.srtp, android.R.layout.simple_list_item_1));


        LoadUserInfo();
        setOnlineStatus(null);

        activity.receiver.broadcastReceiver = this;
        view.findViewById(R.id.btonline).setOnClickListener(this);
        view.findViewById(R.id.btoffline).setOnClickListener(this);

        /**
         * 自己增加
         */
        // 监听界面加载完毕
//        ViewTreeObserver viewTreeObserver = button.getViewTreeObserver();
//        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                // 界面加载完毕后移除监听
//                button.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//
//                // 延迟执行点击事件
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        button.performClick();
//                    }
//                }, 500); // 延迟时间，单位毫秒
//            }
//        });
        /**
         * 自己增加
         */
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            activity.receiver.broadcastReceiver = this;
            setOnlineStatus(null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        activity.receiver.broadcastReceiver = null;
    }


    private void LoadUserInfo() {
//        619094   aa123456     pbx.sacsv.io:7218
        etUsername.setText(sharedPreferences.getString(PortSipService.USER_NAME, "619097"));
        etPassword.setText(sharedPreferences.getString(PortSipService.USER_PWD, "aa123456"));
        etSipServer.setText(sharedPreferences.getString(PortSipService.SVR_HOST, "pbx.sacsv.io"));
        etSipServerPort.setText(sharedPreferences.getString(PortSipService.SVR_PORT, "7218"));

        etDisplayName.setText(sharedPreferences.getString(PortSipService.USER_DISPALYNAME, null));
        etUserDomain.setText(sharedPreferences.getString(PortSipService.USER_DOMAIN, null));
        etAuthName.setText(sharedPreferences.getString(PortSipService.USER_AUTHNAME, null));
        etStunServer.setText(sharedPreferences.getString(PortSipService.STUN_HOST, null));
        etStunPort.setText(sharedPreferences.getString(PortSipService.STUN_PORT, "3478"));

        spTransport.setSelection(sharedPreferences.getInt(PortSipService.TRANS, 0));
        spSRTP.setSelection(sharedPreferences.getInt(PortSipService.SRTP, 0));
    }

    private void SaveUserInfo() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(PortSipService.USER_NAME, "619097");
//        editor.putString(PortSipService.USER_PWD, "aa123456");
//        editor.putString(PortSipService.SVR_HOST, " pbx.sacsv.io");
//        editor.putString(PortSipService.SVR_PORT, "7218");
        editor.putString(PortSipService.USER_NAME, etUsername.getText().toString());
        editor.putString(PortSipService.USER_PWD, etPassword.getText().toString());
        editor.putString(PortSipService.SVR_HOST, etSipServer.getText().toString());
        editor.putString(PortSipService.SVR_PORT, etSipServerPort.getText().toString());

        editor.putString(PortSipService.USER_DISPALYNAME, etDisplayName.getText().toString());
        editor.putString(PortSipService.USER_DOMAIN, etUserDomain.getText().toString());
        editor.putString(PortSipService.USER_AUTHNAME, etAuthName.getText().toString());
        editor.putString(PortSipService.STUN_HOST, etStunServer.getText().toString());
        editor.putString(PortSipService.STUN_PORT, etStunPort.getText().toString());

        editor.commit();
    }


    public void onBroadcastReceiver(Intent intent) {
        String action = intent == null ? "" : intent.getAction();
        if (PortSipService.REGISTER_CHANGE_ACTION.equals(action)) {
            String tips = intent.getStringExtra(EXTRA_REGISTER_STATE);
            setOnlineStatus(tips);
        } else if (PortSipService.CALL_CHANGE_ACTION.equals(action)) {
            //long sessionId = intent.GetLongExtra(PortSipService.EXTRA_CALL_SEESIONID, Session.INVALID_SESSION_ID);
            //callStatusChanged(sessionId);
        }
    }

    private void setOnlineStatus(String tips) {
        if (CallManager.Instance().isRegistered) {
            tvStatus.setText(TextUtils.isEmpty(tips) ? getString(R.string.online) : tips);
        } else {
            tvStatus.setText(TextUtils.isEmpty(tips) ? getString(R.string.offline) : tips);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btonline) {

            if (CallManager.Instance().online) {
                Toast.makeText(getActivity(), "Please OffLine First", Toast.LENGTH_SHORT).show();
            } else {
                SaveUserInfo();
                Intent onLineIntent = new Intent(getActivity(), PortSipService.class);
                onLineIntent.setAction(PortSipService.ACTION_SIP_REGIEST);
                PortSipService.startServiceCompatibility(getActivity(), onLineIntent);
                tvStatus.setText("RegisterServer..");
            }
        } else if (view.getId() == R.id.btoffline) {

            Intent offLineIntent = new Intent(getActivity(), PortSipService.class);
            offLineIntent.setAction(PortSipService.ACTION_SIP_UNREGIEST);
            PortSipService.startServiceCompatibility(getActivity(), offLineIntent);
            tvStatus.setText("unRegisterServer");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapterView == null)
            return;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (adapterView.getId() == R.id.spSRTP) {
            editor.putInt(PortSipService.SRTP, position).commit();
        } else if (adapterView.getId() == R.id.spTransport) {
            editor.putInt(PortSipService.TRANS, position).commit();
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
