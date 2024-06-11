package com.spins.intech.login.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import com.app.module.base.theme.AppTheme
import com.app.module.base.support.AppRouterConfig
import com.app.module.base.view.BaseBusinessAct
import com.xiaojinzi.component.anno.RouterAnno
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.compose.StateBar
import com.xiaojinzi.support.init.BootView
import com.xiaojinzi.support.ktx.initOnceUseViewModel
import com.xiaojinzi.support.ktx.translateStatusBar
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.xxx,
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

    }

}