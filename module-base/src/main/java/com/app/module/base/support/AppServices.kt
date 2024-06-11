package com.app.module.base.support

import com.app.module.base.spi.AppInfoSpi
import com.app.module.base.spi.SystemSpi
import com.app.module.base.spi.UserSpi
import com.xiaojinzi.component.impl.service.ServiceManager

object AppServices {

    const val TAG = "AppServices"

    val systemSpi: SystemSpi?
        get() = ServiceManager.get(tClass = SystemSpi::class)

    val appInfoSpi: AppInfoSpi by lazy {
        ServiceManager.requiredGet(tClass = AppInfoSpi::class)
    }

    val userSpi: UserSpi by lazy {
        ServiceManager.requiredGet(tClass = UserSpi::class)
    }

    fun destroySpiAboutTallyDatabase() {
    }

}