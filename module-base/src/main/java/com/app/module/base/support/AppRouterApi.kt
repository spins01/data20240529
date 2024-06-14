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
interface AppRouterApi {
    @HostAndPathAnno(LOGIN_ACTIVITY)
    fun toLoginView(@UiContext context: Context)

    @HostAndPathAnno(ACCOUNT_ACTIVITY)
    fun toAccountView(@UiContext context: Context)
}
