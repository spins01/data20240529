package com.app.module.base.common




interface CommonInterface {
    //登录接口
    suspend fun login(userName:String,password:String,callback: CommonNothingCallback)
}