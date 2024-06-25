package com.intech.net.init

import android.util.Log
import com.app.module.base.bean.BasePageResponse
import com.google.gson.reflect.TypeToken
import okhttp3.Response
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.TypeParser
import rxhttp.wrapper.utils.convertTo
import java.lang.reflect.Type

@Parser(name = "PageResponse", wrappers = [MutableList::class,List::class])
open class PageResponseParser<T> : TypeParser<BasePageResponse<T>> {
    protected constructor() : super()
    constructor(type: Type) : super()
    override fun onParse(response: Response): BasePageResponse<T> {
          val data : BasePageResponse<T> = response.convertTo(BasePageResponse::class,*types)
          if(data.code!=0){
              throw ParseException(data.code.toString(),data.count.toString(),response)
          }
        return data
    }
}