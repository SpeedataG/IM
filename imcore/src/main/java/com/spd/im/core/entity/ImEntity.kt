package com.spd.im.core.entity

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

/**
 * @author :Reginer on  2020/5/13 15:32.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
@Keep
@Parcelize
open class ImEntity(
    /**
     * 消息id，由服务器生成，发送时为null，后台处理后变成消息主键，不重复
     */
    var msgId: String? = null,

    /**
     * 消息标识符，发送时生成，服务器不处理
     */
    var tag: String? = null,

    /**
     * 规定系统消息为一个具体字符
     */
    var fromId: String? = null,
    var fromName: String? = null,

    /**
     * 发送人头像
     */
    var fromUrl: String? = null,

    /**
     * 接收人id，接收群id
     */
    var toId: String? = null,
    var toName: String? = null,
    var toUrl: String? = null,

    /**
     * 小图
     */
    var imageUrlSmall: String? = null,

    /**
     * 大图(大图小图实际是一张，可用一个，另一个预留)
     */
    var imageUrlLarge: String? = null,

    /**
     * 语音地址
     */
    var voiceUrl: String? = null,

    /**
     * 语音时间
     */
    var voiceTime: Int = 0,

    /**
     * 文本消息
     */
    var content: String? = null,

    /**
     * 1单聊，2群聊
     */
    var typeChat: Int = 0,

    /**
     * 1文字 2图片 3声音
     */
    var typeFile: Int = 0,

    /**
     * 消息时间，服务器赋值
     */
    var time: Long = 0,

    /**
     * 0失败 1成功 2正在发送  3正在下载
     */
    var sendState: Int = 0,

    /**
     * 0未读  1已读
     */
    var readState: Int = 0
) : Parcelable {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}