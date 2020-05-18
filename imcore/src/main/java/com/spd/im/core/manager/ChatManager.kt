package com.spd.im.core.manager

import com.spd.im.core.entity.ImEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 * @author :Reginer on  2020/5/16 11:34.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
fun ImEntity.sendText(){
    val message = this.toString()
    GlobalScope.launch {
        RxHttp.postJson("http://bj.speedata.cn:9004/monitor/api/msg/text").addAll(message)
            .toResponse<ImEntity>().await()
    }

}