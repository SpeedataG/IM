package com.spd.im.core.manager

import android.content.Context
import com.google.gson.Gson
import com.spd.im.core.entity.ImEntity
import com.spd.im.core.listener.ILoginCallback
import com.spd.im.core.listener.ImMessageListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.util.*

/**
 * @author :Reginer on  2020/5/15 14:49.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
class ImChatManager : MqttCallbackExtended {
    private val messageListeners =
        Collections.synchronizedList(ArrayList<ImMessageListener>())
    private var idSelf: String? = null
    fun addMessageListener(listener: ImMessageListener?) {
        if (listener == null) {
            return
        }
        if (!messageListeners.contains(listener)) {
            messageListeners.add(listener)
        }
    }

    fun removeMessageListener(listener: ImMessageListener?) {
        if (listener == null) {
            return
        }
        messageListeners.remove(listener)
    }

    fun connect(
        context: Context?,
        username: String?,
        iLoginCallback: ILoginCallback?
    ) {
        idSelf = username
        MqttManager.getInstance().connect(context, username, this, iLoginCallback)
    }

    fun logOut() {
        MqttManager.getInstance().disconnect()
        MqttManager.getInstance().release()
    }

    suspend fun sendText(imEntity: ImEntity): ImEntity {
        return imEntity.sendText()
    }

    suspend fun sendVoice(imEntity: ImEntity, voicePath: String): ImEntity {
        return imEntity.sendVoice(voicePath)
    }


    override fun connectComplete(
        reconnect: Boolean,
        serverURI: String
    ) {
        val topicSelf = "msg/chat/$idSelf"
        MqttManager.getInstance().subscribeMsg(arrayOf(topicSelf), intArrayOf(2))
    }

    override fun connectionLost(cause: Throwable) {}
    override fun messageArrived(topic: String, message: MqttMessage) {
        val jsonMessage = String(message.payload)
        val imEntity = Gson().fromJson(jsonMessage, ImEntity::class.java)
        for (imMessageListener in messageListeners) {
            imMessageListener.onMessageReceived(imEntity)
        }
    }

    override fun deliveryComplete(token: IMqttDeliveryToken) {}
}