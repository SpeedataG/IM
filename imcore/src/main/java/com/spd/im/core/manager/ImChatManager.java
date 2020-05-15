package com.spd.im.core.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.spd.im.core.entity.ImEntity;
import com.spd.im.core.listener.ILoginCallback;
import com.spd.im.core.listener.ImMessageListener;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author :Reginer on  2020/5/15 14:49.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public class ImChatManager implements MqttCallbackExtended {
    private final List<ImMessageListener> messageListeners = Collections.synchronizedList(new ArrayList<ImMessageListener>());
    private String idSelf;

    public void addMessageListener(ImMessageListener listener) {
        if (listener == null) {
            return;
        }
        if (!messageListeners.contains(listener)) {
            messageListeners.add(listener);
        }
    }

    public void removeMessageListener(ImMessageListener listener) {
        if (listener == null) {
            return;
        }
        messageListeners.remove(listener);
    }

    public void connect(Context context, String username, ILoginCallback iLoginCallback) {
        idSelf = username;
        MqttManager.getInstance().connect(context, username, this, iLoginCallback);
    }

    public void logOut() {
        MqttManager.getInstance().disconnect();
        MqttManager.getInstance().release();
    }

    public void sendText() {

    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        final String topicSelf = "msg/chat/" + idSelf;
        MqttManager.getInstance().subscribeMsg(new String[]{topicSelf}, new int[]{2});
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        final String jsonMessage = new String(message.getPayload());
        final ImEntity imEntity = new Gson().fromJson(jsonMessage, ImEntity.class);
        for (ImMessageListener imMessageListener : messageListeners) {
            imMessageListener.onMessageReceived(imEntity);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
