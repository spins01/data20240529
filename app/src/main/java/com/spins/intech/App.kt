package com.spins.intech

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import com.app.module.base.ktx.getProcessName
import com.app.module.base.support.DevelopHelper
import com.tencent.mmkv.MMKV
import com.xiaojinzi.component.Component
import com.xiaojinzi.component.Config
import com.xiaojinzi.component.error.ignore.ErrorIgnore
import com.xiaojinzi.component.support.ASMUtil
import com.xiaojinzi.reactive.template.ReactiveTemplate
import com.xiaojinzi.support.activity_stack.ActivityStack
import com.xiaojinzi.support.init.AppInstance
import com.xiaojinzi.support.init.CheckInit
import com.xiaojinzi.support.ktx.LogSupport
import com.xiaojinzi.support.ktx.orNull
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.model.exception.CommonBusinessException


class App : Application() {

    private val tag = "App"

    override fun attachBaseContext(baseContext: Context) {
        super.attachBaseContext(baseContext)
        AppInstance.app = this
        AppInstance.isDebug = BuildConfig.DEBUG
        LogSupport.logAble = BuildConfig.DEBUG
        DevelopHelper.init(BuildConfig.DEBUG)
    }

    override fun onCreate() {
        super.onCreate()

        val processName = this.getProcessName()
        if (processName != packageName) {
            LogSupport.d(
                tag = tag,
                content = "不是主进程, 不给初始化",
            )
            return
        }

        // 阻止系统恢复
        CheckInit.init(
            app = this,
            bootActAction = "app_boot_20240527",
            bootActCategory = Intent.CATEGORY_DEFAULT,
            rebootActAction = "app_reboot_20240527",
            rebootActCategory = Intent.CATEGORY_DEFAULT,
        )

        ActivityStack.init(
            this, BuildConfig.DEBUG,
        )

        ReactiveTemplate.config(
            isDebug = BuildConfig.DEBUG,
            errorCustom = { error ->
                when (error) {

                    is CommonBusinessException -> {
                        error.message.orNull()?.toStringItemDto()
                    }

                    else -> null
                }
            },
            errorCustomIgnore = { error ->
                when {
                    ErrorIgnore.isIgnore(
                        throwable = error,
                    ) -> {
                        true
                    }

                    else -> false
                }
            }
        )

        LogSupport.d(
            tag = tag,
            content = "所有模块: ${ASMUtil.getModuleNames().joinToString()}",
        )

        // 初始化组件化
        Component.init(
            application = this,
            isDebug = BuildConfig.DEBUG,
            config = Config.Builder()
                .initRouterAsync(true)
                .errorCheck(true)
                .optimizeInit(true)
                .autoRegisterModule(true)
                .build()
        )
        val rootDir = MMKV.initialize(this)
    }



    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

}