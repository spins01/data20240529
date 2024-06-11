package com.app.module.base.support

import android.app.ActivityManager
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import com.xiaojinzi.support.ktx.app

fun finishAppAllTask() {
    (app.getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager).appTasks?.forEach {
        it.finishAndRemoveTask()
    }
}

fun isInstallWx(): Boolean {
    return try {
        app.packageManager.getPackageInfo("com.tencent.mm", PackageManager.GET_ACTIVITIES)
        true
    } catch (e: Exception) {
        false
    }
}