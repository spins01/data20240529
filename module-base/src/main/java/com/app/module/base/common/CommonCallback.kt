package com.app.module.base.common

interface CommonCallback<T> {
    fun onSuccess(t:T)
    fun onError(errorMessage:String)
}