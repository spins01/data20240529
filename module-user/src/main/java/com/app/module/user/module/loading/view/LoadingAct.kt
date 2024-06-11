package com.app.module.user.module.loading.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import com.xiaojinzi.component.anno.RouterAnno
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.compose.StateBar
import com.xiaojinzi.support.init.BootView
import com.xiaojinzi.support.ktx.initOnceUseViewModel
import com.xiaojinzi.support.ktx.translateStatusBar
import com.app.module.base.theme.AppTheme
import com.app.module.base.support.AppRouterConfig
import com.app.module.base.view.BaseBusinessAct
import com.app.module.user.module.loading.domain.LoadingIntent
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.USER_LOADING,
)
@BootView
@ViewLayer
class LoadingAct : BaseBusinessAct<LoadingViewModel>() {

    override fun getViewModelClass(): Class<LoadingViewModel> {
        return LoadingViewModel::class.java
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
            requiredViewModel().addIntent(
                intent = LoadingIntent.GO(
                    context = mContext,
                ),
            )
        }

        setContent {
            AppTheme {
                StateBar {
                    LoadingViewWrap()
                }
            }
        }

    }

}