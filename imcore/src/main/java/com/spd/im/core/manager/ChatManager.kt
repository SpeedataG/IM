package com.spd.im.core.manager

import android.text.TextUtils
import com.spd.im.core.ImClient
import com.spd.im.core.entity.ImEntity
import com.spd.im.core.entity.ImUserEntity
import com.spd.im.core.net.ImUrls
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 * @author :Reginer on  2020/5/16 11:34.
 * 联系方式:QQ:282921012
 * 功能描述:
 */

/**
 * 发送消息
 */
suspend fun ImEntity.sendText(): ImEntity {
    val requestUrl = ImUrls.DEFAULT_PREFIX + ImClient.getInstance().imOptions.host + ImUrls.COLON + ImClient.getInstance().imOptions.port + ImUrls.SEND_MSG
    return RxHttp.postJson(requestUrl).addAll(this.toString()).toResponse<ImEntity>().await()
}

/**
 * 发送语音消息
 */
suspend fun ImEntity.sendVoice(voicePath: String): ImEntity {
    val requestUrl = ImUrls.DEFAULT_PREFIX + ImClient.getInstance().imOptions.host + ImUrls.COLON + ImClient.getInstance().imOptions.port + ImUrls.SEND_VOICE
    return RxHttp.postForm(requestUrl).add("entity", this.toString()).addFile("voice", voicePath).toResponse<ImEntity>().await()
}

/**
 * 更新用户信息
 */
suspend fun ImUserEntity.update(): ImUserEntity {
    val requestUrl = ImUrls.DEFAULT_PREFIX + ImClient.getInstance().imOptions.host + ImUrls.COLON + ImClient.getInstance().imOptions.port + ImUrls.USER_UPDATE
    val updateRequest = RxHttp.postForm(requestUrl).add("cardId", cardId).add("userName", userName)
    if (TextUtils.isEmpty(img).not() && img?.startsWith("http") == false) {
        updateRequest.addFile("img", img)
    }
    return updateRequest.toResponse<ImUserEntity>().await()
}