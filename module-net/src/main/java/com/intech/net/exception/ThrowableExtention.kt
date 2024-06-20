package com.intech.net.exception

import rxhttp.wrapper.exception.HttpStatusCodeException
import rxhttp.wrapper.exception.ParseException


val Throwable.spinsCode: Int
    get() =
        when (this) {
            is HttpStatusCodeException -> this.statusCode //Http状态码异常
            is ParseException -> this.errorCode.toIntOrNull() ?: -1     //业务code异常
            else -> -1
        }
val Throwable.spinsMessage:String
    get() =
        when (this.spinsCode) {
            2002 -> "Incorrect username or password" //Http状态码异常
               //业务code异常
            else -> "unknown error"
        }






