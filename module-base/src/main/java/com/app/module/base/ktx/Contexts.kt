package com.app.module.base.ktx

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Process

/**
 * 获取进程的名字
 */
fun Context.getProcessName(): String? {
    return try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Application.getProcessName()
        } else {
            val pid = Process.myPid()
            val am = this.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
            am?.runningAppProcesses
                ?.find { it.pid == pid }
                ?.processName
        }
    } catch (_: Exception) {
        null
    }
}