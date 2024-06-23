package com.intech.net.impl

import com.app.module.base.bean.UserInfoBean
import com.app.module.base.common.CommonInterface
import com.app.module.base.common.CommonNothingCallback
import com.app.module.base.common.CommonObjCallback
import com.app.module.base.extension.SPINS_TOKEN
import com.app.module.base.extension.SharedPreferenceUtil
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
}