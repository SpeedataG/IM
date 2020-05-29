package com.spd.im.core.manager

import com.spd.im.core.entity.ImEntity
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 * @author :Reginer on  2020/5/16 11:34.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
suspend fun ImEntity.sendText(): ImEntity {
    return RxHttp.postJson("http://bj.speedata.cn:9004/monitor/api/msg/text")
        .addAll(this.toString()).toResponse<ImEntity>().await()
}