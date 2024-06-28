package com.app.module.base.common

import com.app.module.base.bean.BasePageResponse
import com.app.module.base.bean.SearchBean
import com.app.module.base.bean.UserInfoBean


interface CommonInterface {
    //登录接口
    suspend fun login(userName:String,password:String,callback: CommonObjCallback<UserInfoBean>)

    suspend fun logOut(callback: CommonNothingCallback)

    suspend fun search(userName:String,currentPage:Int?,pageSize:Int,startTime:String,endTime:String,callback: CommonListCallback<SearchBean>)
    suspend fun call(userName:String,callback: CommonNothingCallback)

}