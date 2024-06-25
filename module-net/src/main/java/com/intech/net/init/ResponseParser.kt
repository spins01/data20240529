package com.intech.net.init

import com.app.module.base.bean.BasePageResponse
import com.app.module.base.bean.BaseResponse
import okhttp3.Response
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.TypeParser
import rxhttp.wrapper.utils.convertTo
import java.lang.reflect.Type

@Parser(name = "Response", wrappers = [MutableList::class,List::class])
open class ResponseParser<T> : TypeParser<T> {
    protected constructor() : super()
    constructor(type: Type) : super(type)

    override fun onParse(response: Response): T {
            val data: BaseResponse<T> = response.convertTo(BaseResponse::class, *types)
            var t = data.data
            if(data.code!=0){
                throw ParseException(data.code.toString(),data.message,response)
            }
            if(t == null){
                t = data.message as T
//                t = if(data.token == null || data.token.isEmpty()){data.message as T}else{data.token as T}
            }
            return t
    }
}