package com.spd.im.core.manager

import com.spd.im.core.ImClient
import com.spd.im.core.entity.ImEntity
import com.spd.im.core.net.ImUrls
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 * @author :Reginer on  2020/5/16 11:34.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
suspend fun ImEntity.sendText(): ImEntity {
    val sendUrl = ImUrls.DEFAULT_PREFIX + ImClient.getInstance().imOptions.host + ImClient.getInstance().imOptions.port + ImUrls.SEND_MSG
    return RxHttp.postJson(sendUrl).addAll(this.toString()).toResponse<ImEntity>().await()
}