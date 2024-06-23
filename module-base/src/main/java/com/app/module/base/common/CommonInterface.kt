package com.app.module.base.common

import com.app.module.base.bean.UserInfoBean


interface CommonInterface {
    //登录接口
    suspend fun login(userName:String,password:String,callback: CommonObjCallback<UserInfoBean>)

    suspend fun logOut(callback: CommonNothingCallback)
}