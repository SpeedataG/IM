package com.spd.im.core.net

/**
 * @author :Reginer in  2020/6/1 9:47.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
object ImUrls {
    const val DEFAULT_HOST = "bj.speedata.cn"
    const val DEFAULT_PORT = 9004
    const val DEFAULT_PREFIX = "http://"
    const val COLON = ":"

    /**
     * 发送消息
     */
    const val SEND_MSG = "/monitor/api/msg/text"

    /**
     * 发送语音
     */
    const val SEND_VOICE = "/monitor/api/msg/voice"

    /**
     * 更新用户
     */
    const val USER_UPDATE = "/monitor/api/device/update"
}