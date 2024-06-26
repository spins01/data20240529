package com.intech.net

import android.app.Application
import android.util.Log
import com.app.module.base.extension.Authorization
import com.app.module.base.extension.SPINS_TOKEN
import com.app.module.base.extension.SharedPreferenceUtil
import com.module.net.BuildConfig
import com.xiaojinzi.component.anno.ModuleAppAnno
import com.xiaojinzi.component.application.IApplicationLifecycle
import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.param.Param
import rxhttp.wrapper.ssl.HttpsUtils
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession






@ModuleAppAnno
class NetModuleApplication: IApplicationLifecycle {
    override fun onCreate(app: Application) {
        initRxHttp()
    }

    override fun onDestroy() {

    }
    private fun initRxHttp() {
      val sslParams = HttpsUtils.getSslSocketFactory()
//        val client: OkHttpClient = OkHttpClient.Builder()
//            .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager) //添加信任证书
//            .hostnameVerifier(HostnameVerifier { _: String?, _: SSLSession? -> true }) //忽略host验证
//            .build()
        RxHttpPlugins.init(null) //自定义OkHttpClient对象
            .setDebug(BuildConfig.DEBUG, false, 2) //调试模式/分段打印/json数据格式化输出
            .setOnParamAssembly { p: Param<*>? ->
                Log.i("马超","我的token0:${SharedPreferenceUtil.getString(SPINS_TOKEN)}")
                if(SharedPreferenceUtil.getString(SPINS_TOKEN)!=null&& SharedPreferenceUtil.getString(
                        SPINS_TOKEN
                    )!!.isNotEmpty()
                ){
                    Log.i("马超","我的token1:${SharedPreferenceUtil.getString(SPINS_TOKEN)}")
                    p?.addHeader(Authorization,"Token ${SharedPreferenceUtil.getString(SPINS_TOKEN)}")
                }
            }
    }
}