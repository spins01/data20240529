package com.intech.net.impl

import com.app.module.base.bean.SearchBean
import com.app.module.base.bean.UserInfoBean
import com.app.module.base.common.CommonInterface
import com.app.module.base.common.CommonListCallback
import com.app.module.base.common.CommonNothingCallback
import com.app.module.base.common.CommonObjCallback
import com.intech.net.constant.spinsPassword
import com.intech.net.constant.spinsUsername
import com.intech.net.exception.spinsMessage
import com.xiaojinzi.component.anno.ServiceAnno
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
                callback.onError(it.spinsMessage)
            }
            .collect {
                 callback.onSuccess(it)
            }
    }

    override suspend fun logOut(callback: CommonNothingCallback) {
         RxHttp.postJson(com.intech.net.constant.logOut)
             .toFlowResponse<String>()
             .catch { callback.onError(it.spinsMessage) }
             .collect{
                 callback.onSuccess()
             }
    }

    override suspend fun search(userName: String,currentPage:Int?,pageSize:Int,callback:CommonListCallback<SearchBean>) {
        val params = mapOf(spinsUsername to userName)
         RxHttp.postJson("${com.intech.net.constant.search}?page_size=$pageSize&page=$currentPage")
             .addAll(params)
             .toFlowResponse<List<SearchBean>>()
             .catch {
                 callback.onError(it.spinsMessage)
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
            .catch { callback.onError(it.spinsMessage) }
            .collect{
                callback.onSuccess()
            }
    }
}