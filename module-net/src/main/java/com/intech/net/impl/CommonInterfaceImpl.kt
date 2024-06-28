package com.intech.net.impl

import android.app.Activity
import com.app.module.base.bean.SearchBean
import com.app.module.base.bean.UserInfoBean
import com.app.module.base.common.CommonInterface
import com.app.module.base.common.CommonListCallback
import com.app.module.base.common.CommonNothingCallback
import com.app.module.base.common.CommonObjCallback
import com.app.module.base.extension.APP_ACTIVITY_FLAG_LOGIN
import com.app.module.base.extension.SPINS_TOKEN
import com.app.module.base.extension.SharedPreferenceUtil
import com.intech.net.constant.end_date
import com.intech.net.constant.spinsPassword
import com.intech.net.constant.spinsUsername
import com.intech.net.constant.start_date
import com.intech.net.exception.spinsCode
import com.intech.net.exception.spinsMessage
import com.xiaojinzi.component.anno.ServiceAnno
import com.xiaojinzi.support.activity_stack.ActivityStack
import kotlinx.coroutines.flow.catch
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toFlowResponse

@ServiceAnno(CommonInterface::class)
class CommonInterfaceImpl : CommonInterface {
    override suspend fun login(userName: String, password: String,callback: CommonObjCallback<UserInfoBean>) {
        val params = mapOf(spinsUsername to userName, spinsPassword to password)
        RxHttp.postJson(com.intech.net.constant.login)
            .addAll(params)
            .toFlowResponse<UserInfoBean>()
            .catch {
                if(handleStatusCode(it.spinsCode)){
                    callback.onError("Another user has logged into your account. You need to log in again.")
                    return@catch
                }
                callback.onError(it.message.toString())
            }
            .collect {
                 callback.onSuccess(it)
            }
    }

    override suspend fun logOut(callback: CommonNothingCallback) {
         RxHttp.postJson(com.intech.net.constant.logOut)
             .toFlowResponse<String>()
             .catch {
                 if(handleStatusCode(it.spinsCode)){
                     callback.onError("Another user has logged into your account. You need to log in again.")
                     return@catch
                 }
                 callback.onError(it.message.toString())
             }
             .collect{
                 callback.onSuccess()
             }
    }

    override suspend fun search(userName: String,currentPage:Int?,pageSize:Int,startTime:String,endTime:String,callback:CommonListCallback<SearchBean>) {
        val params = mapOf(spinsUsername to userName, start_date to startTime, end_date to end_date)
         RxHttp.postJson("${com.intech.net.constant.search}?page_size=$pageSize&page=$currentPage")
             .addAll(params)
             .toFlowResponse<List<SearchBean>>()
             .catch {
                 if(handleStatusCode(it.spinsCode)){
                     callback.onError("Another user has logged into your account. You need to log in again.")
                     return@catch
                 }
                 callback.onError(it.message.toString())
             }
             .collect{
                 callback.onSuccess(it)
             }
    }

    override suspend fun call(userName: String, callback: CommonNothingCallback) {
        val params = mapOf(spinsUsername to userName)
        RxHttp.postJson(com.intech.net.constant.call)
            .addAll(params)
            .toFlowResponse<String>()
            .catch {
                if(handleStatusCode(it.spinsCode)){
                    callback.onError("Another user has logged into your account. You need to log in again.")
                    return@catch
                }
                callback.onError(it.message.toString())
            }
            .collect{
                callback.onSuccess()
            }
    }
    private fun handleStatusCode(statusCode:Int) :Boolean{
        if(statusCode == 401){
            SharedPreferenceUtil.deleteValueForKey(SPINS_TOKEN)
            ActivityStack.finish{act ->  act.noFlag(APP_ACTIVITY_FLAG_LOGIN)}
            return true
        }else {
            return false
        }
    }
}