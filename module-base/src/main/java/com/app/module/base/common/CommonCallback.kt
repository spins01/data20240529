package com.app.module.base.common

interface CommonNothingCallback {
    fun onSuccess()
    fun onError(errorMessage: String)

}
interface CommonObjCallback<T> {
    fun onSuccess(t:T)
    fun onError(errorMessage: String)

}
interface CommonListCallback<T> {
    fun onSuccess(list:List<T>)
    fun onError(errorMessage: String)

}

