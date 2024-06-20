package com.intech.net.impl

import android.util.Log
import com.app.module.base.common.CommonCallback
import com.app.module.base.common.CommonInterface
import com.intech.net.constant.spinsPassword
import com.intech.net.constant.spinsUsername
import com.intech.net.exception.spinsCode
import com.intech.net.exception.spinsMessage
import com.xiaojinzi.component.anno.ServiceAnno
import kotlinx.coroutines.flow.catch
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toFlowResponse

@ServiceAnno(CommonInterface::class)
class CommonInterfaceImpl : CommonInterface {
    override suspend fun login(userName: String, password: String,callback: CommonCallback<String>) {
        val params = mapOf(spinsUsername to userName, spinsPassword to password)
        RxHttp.postJson(com.intech.net.constant.login)
            .addAll(params)
            .toFlowResponse<String>()
            .catch {
                callback.onError(it.spinsMessage)
            }
            .collect {
                 callback.onSuccess("")
            }
    }
}