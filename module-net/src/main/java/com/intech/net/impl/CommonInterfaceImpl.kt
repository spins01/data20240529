package com.intech.net.impl

import android.util.Log
import com.app.module.base.common.CommonInterface
import com.intech.net.constant.spinsPassword
import com.intech.net.constant.spinsUsername
import com.xiaojinzi.component.anno.ServiceAnno
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import rxhttp.toFlow
import rxhttp.wrapper.param.RxHttp

@ServiceAnno(CommonInterface::class)
class CommonInterfaceImpl : CommonInterface {
    override suspend fun login(userName: String, password: String) {
        val params = mapOf(spinsUsername to userName, spinsPassword to password)
        RxHttp.postJson(com.intech.net.constant.login)
            .addAll(params)
            .toFlow<String>()
            .collect {
                Log.i("马超", it)
            }
    }
}