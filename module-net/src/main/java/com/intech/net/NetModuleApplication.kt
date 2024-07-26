package com.intech.net

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.app.module.base.extension.Authorization
import com.app.module.base.extension.SPINS_TOKEN
import com.app.module.base.extension.SharedPreferenceUtil
import com.module.net.BuildConfig
import com.xiaojinzi.component.anno.ModuleAppAnno
import com.xiaojinzi.component.application.IApplicationLifecycle
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.param.Param
import rxhttp.wrapper.ssl.HttpsUtils


@ModuleAppAnno
class NetModuleApplication: IApplicationLifecycle {
    override fun onCreate(app: Application) {
        initRxHttp()
    }

    override fun onDestroy() {

    }
    @SuppressLint("SuspiciousIndentation")
    private fun initRxHttp() {
      val sslParams = HttpsUtils.getSslSocketFactory()
//        val client: OkHttpClient = OkHttpClient.Builder()
//            .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager) //添加信任证书
//            .hostnameVerifier(HostnameVerifier { _: String?, _: SSLSession? -> true }) //忽略host验证
//            .build()
        RxHttpPlugins.init(null) //自定义OkHttpClient对象
            .setDebug(BuildConfig.DEBUG, false, 2) //调试模式/分段打印/json数据格式化输出
            .setOnParamAssembly { p: Param<*>? ->
                val spinsToken = SharedPreferenceUtil.getString(SPINS_TOKEN)
                Log.i("马超","我的token0:$spinsToken")
                if(!spinsToken.isNullOrEmpty()){
                    Log.i("马超","我的token1:$spinsToken")
                    p?.addHeader(Authorization,"Token $spinsToken")
                }
            }
    }
}