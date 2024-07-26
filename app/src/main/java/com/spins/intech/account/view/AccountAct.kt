package com.spins.intech.account.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import com.app.module.base.extension.APP_ACTIVITY_FLAG_ACCOUNT
import com.app.module.base.support.ACCOUNT_ACTIVITY
import com.app.module.base.theme.AppTheme
import com.app.module.base.support.AppRouterConfig
import com.app.module.base.view.BaseBusinessAct
import com.spins.intech.sipsample.service.PortSipService
import com.spins.intech.sipsample.ui.MainActivity
import com.xiaojinzi.component.anno.RouterAnno
import com.xiaojinzi.support.activity_stack.ActivityFlag
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.compose.StateBar
import com.xiaojinzi.support.ktx.initOnceUseViewModel
import com.xiaojinzi.support.ktx.translateStatusBar
import com.xiaojinzi.tally.lib.res.ui.APP_ACTIVITY_FLAG_LOGIN
import kotlinx.coroutines.InternalCoroutinesApi
@ActivityFlag(
    APP_ACTIVITY_FLAG_ACCOUNT,
)
@RouterAnno(
    hostAndPath = ACCOUNT_ACTIVITY,
)
@ViewLayer
class AccountAct : BaseBusinessAct<AccountViewModel>() {

    override fun getViewModelClass(): Class<AccountViewModel> {
        return AccountViewModel::class.java
    }

    @OptIn(
        InternalCoroutinesApi::class,
        ExperimentalMaterial3Api::class,
        ExperimentalAnimationApi::class,
        ExperimentalFoundationApi::class,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.translateStatusBar()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        initOnceUseViewModel {
        }

        setContent {
            AppTheme {
                StateBar {
                    AccountViewWrap()
                }
            }
        }

    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            2 -> {
                var i = 0
                for (result in grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "you must grant the permission " + permissions[i], Toast.LENGTH_SHORT).show()
                        i++
                        stopService(Intent(this, PortSipService::class.java))
                        System.exit(0)
                    }else{
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
            }
        }
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//    String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case REQ_DANGERS_PERMISSION:
//            int i=0;
//            for(int result:grantResults) {
//            if (result != PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "you must grant the permission "+permissions[i], Toast.LENGTH_SHORT).show();
//                i++;
//                stopService(new Intent(this, PortSipService.class));
//                System.exit(0);
//            }
//        }
//            break;
//        }
//    }
}