package com.app.module.base.support

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.UiContext
import com.xiaojinzi.component.anno.router.AfterRouteActionAnno
import com.xiaojinzi.component.anno.router.AfterRouteEventActionAnno
import com.xiaojinzi.component.anno.router.FlagAnno
import com.xiaojinzi.component.anno.router.HostAndPathAnno
import com.xiaojinzi.component.anno.router.NavigateAnno
import com.xiaojinzi.component.anno.router.ParameterAnno
import com.xiaojinzi.component.anno.router.RequestCodeAnno
import com.xiaojinzi.component.anno.router.RouterApiAnno
import com.xiaojinzi.module.common.base.CommonRouterConfig
import com.xiaojinzi.support.ktx.app

@RouterApiAnno
interface AppRouterUserApi {

    @RequestCodeAnno
    @NavigateAnno(resultCodeMatch = Activity.RESULT_OK)
    @HostAndPathAnno(value = AppRouterConfig.USER_PRIVACY_AGREEMENT)
    suspend fun privacyAgreementBySuspend(
        @UiContext context: Context,
        @AfterRouteActionAnno action: () -> Unit = {},
    )

    @HostAndPathAnno(value = AppRouterConfig.USER_LOGIN)
    fun toLoginView(
        @UiContext context: Context,
        @AfterRouteActionAnno action: () -> Unit = {},
    )

    @HostAndPathAnno(value = AppRouterConfig.USER_LOGIN)
    suspend fun toLoginViewSuspend(
        @UiContext context: Context,
        @AfterRouteActionAnno action: () -> Unit = {},
    )

    @FlagAnno(Intent.FLAG_ACTIVITY_NEW_TASK)
    @HostAndPathAnno(value = AppRouterConfig.USER_LOGIN)
    suspend fun toLoginViewInNewTaskSuspend(
        @UiContext context: Context = app,
    )

}

@RouterApiAnno
interface AppRouterBaseApi {
}

@RouterApiAnno
interface AppRouterCustomApi {

    @HostAndPathAnno(value = AppRouterConfig.CUSTOM_SYSTEM_SHARE)
    fun toSystemShareTextView(
        @UiContext context: Context,
        @ParameterAnno("text") text: String,
        @AfterRouteEventActionAnno action: () -> Unit = {},
    )

}

@RouterApiAnno
interface AppRouterSystemApi {

    @HostAndPathAnno(value = CommonRouterConfig.SYSTEM_APP_MARKET)
    fun toSystemAppMarket(
        @UiContext context: Context,
        @AfterRouteEventActionAnno action: () -> Unit = {},
    )

}
