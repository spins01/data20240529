package com.intech.net.init

import com.app.module.base.bean.BaseResponse
import com.app.module.base.bean.ListParent
import okhttp3.Response
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.TypeParser
import rxhttp.wrapper.utils.convertTo
import java.lang.reflect.Type

@Parser(name = "Response", wrappers = [MutableList::class, List::class])
open class ResponseParser<T> : TypeParser<T> {
    protected constructor() : super()
    constructor(type: Type) : super(type)

    override fun onParse(response: Response): T {
        val data: BaseResponse<T> = response.convertTo(BaseResponse::class, *types)
        var t = data.data
        if (data.code != 0) {
            throw ParseException(data.code.toString(), data.message, response)
        }
        if (t == null) {
            t = data.message as T
//                t = if(data.token == null || data.token.isEmpty()){data.message as T}else{data.token as T}
        }
        if (t is List<*>) {
            if (t.size > 0) {
                val t0 = t[0]
                if (t0 is ListParent) {
                    t0.count = data.count
                    t0.links = data.links
                    t0.total_pages = data.total_pages!!
                    t0.current_page = data.current_page!!
                }
            }
        }
        return t
    }
}