package com.intech.net.exception

import android.util.Log
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.exception.ParseException


val Throwable.spinsCode: Int
    get() =
        when (this) {
            is HttpStatusCodeException -> {
                Log.i("赵云", "HttpStatusCodeException:${this.statusCode}")
                this.statusCode
            } //Http状态码异常
            is ParseException -> {
                Log.i("赵云", "ParseException:${this.errorCode}")
                this.errorCode.toIntOrNull() ?: -1
            }    //业务code异常
            else -> -1
        }
val Throwable.spinsMessage: String
    get() =
        when (this.spinsCode) {
            2002 -> {
                Log.i("赵云", "spinsMessage1:${this.spinsCode}")
                "Incorrect username or password"
            } //Http状态码异常
            2022 -> {
                Log.i("赵云", "spinsMessage2:${this.spinsCode}")
                "Missing parameters"
            }
            2003 -> "User is not logged in"
            2010 -> "Permission denied"
            //业务code异常
            else -> {
                Log.i("赵云", "spinsMessage3:${this.spinsCode}")
                "unknown error"
            }
        }






