package com.spd.im.core.parser

import com.spd.im.core.entity.Response
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.AbstractParser
import java.io.IOException
import java.lang.reflect.Type

@Parser(name = "Response", wrappers = [MutableList::class])
open class ResponseParser<T> : AbstractParser<T> {
    protected constructor() : super()

    constructor(type: Type) : super(type)

    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): T {
        val type: Type = ParameterizedTypeImpl[Response::class.java, mType]
        val data: Response<T> = convert(response, type)
        var t = data.data //获取data字段
        if (t == null && mType === String::class.java) {
            @Suppress("UNCHECKED_CAST")
            t = data.msg as T
        }
        if (data.code != 0 || t == null) {
            throw ParseException(data.code.toString(), data.msg, response)
        }
        return t
    }
}