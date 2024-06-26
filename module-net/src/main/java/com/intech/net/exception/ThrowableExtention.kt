package com.intech.net.exception

import android.util.Log
import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.exception.ParseException

//{
//    "code": 2015,
//    "error": "Unbound Agent"
//}
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
            2002 -> "Incorrect username or password"
            2003 -> "User is not logged in"
            2006 -> "Current password is incorrect"
            2009 -> "User ID is required"
            2010 -> "Permission denied"
            2011 -> "Role must be 2 or 3"
            2015 -> "Unbound Agent"
            2022 -> "Missing parameters"
            2023 -> "Missing parameters"
            else -> "unknown error"

        }






