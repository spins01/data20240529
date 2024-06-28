package com.spins.intech.login.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import com.app.module.base.extension.APP_ACTIVITY_FLAG_ACCOUNT
import com.app.module.base.extension.APP_ACTIVITY_FLAG_LOGIN
import com.app.module.base.extension.SPINS_TOKEN
import com.app.module.base.extension.SharedPreferenceUtil
import com.app.module.base.support.AppRouterApi
import com.app.module.base.theme.AppTheme
import com.app.module.base.support.AppRouterConfig
import com.app.module.base.support.LOGIN_ACTIVITY
import com.app.module.base.view.BaseBusinessAct
import com.xiaojinzi.component.anno.RouterAnno
import com.xiaojinzi.component.impl.Router
import com.xiaojinzi.support.activity_stack.ActivityFlag
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.compose.StateBar
import com.xiaojinzi.support.init.BootView
import com.xiaojinzi.support.ktx.initOnceUseViewModel
import com.xiaojinzi.support.ktx.translateStatusBar
import kotlinx.coroutines.InternalCoroutinesApi
@ActivityFlag(
    APP_ACTIVITY_FLAG_LOGIN,
)
@RouterAnno(
    hostAndPath = LOGIN_ACTIVITY,
)
@BootView
@ViewLayer
class LoginAct : BaseBusinessAct<LoginViewModel>() {

    override fun getViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    @OptIn(
        InternalCoroutinesApi::class,
        ExperimentalMaterial3Api::class,
        ExperimentalAnimationApi::class,
        ExperimentalFoundationApi::class,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(SharedPreferenceUtil.getString(SPINS_TOKEN)!=null&& SharedPreferenceUtil.getString(
                SPINS_TOKEN
            )!!.isNotEmpty()
        ) {
            Router.withApi(apiClass = AppRouterApi::class).toAccountView(this)
        }
        window.translateStatusBar()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        initOnceUseViewModel {
        }

        setContent {
            AppTheme {
                StateBar {
                    LoginViewWrap()
                }
            }
        }
//        if(SharedPreferenceUtil.getString(SPINS_TOKEN)!=null&& SharedPreferenceUtil.getString(
//                SPINS_TOKEN
//            )!!.isNotEmpty()
//        ) {
//
//            Router.withApi(apiClass = AppRouterApi::class).toAccountView(this)
//        }
    }

}