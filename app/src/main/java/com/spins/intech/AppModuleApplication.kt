package com.spins.intech

import android.app.Application
import com.xiaojinzi.component.anno.ModuleAppAnno
import com.xiaojinzi.component.application.IApplicationLifecycle

@ModuleAppAnno
class AppModuleApplication: IApplicationLifecycle {

    override fun onCreate(app: Application) {
    }

    override fun onDestroy() {
    }

}