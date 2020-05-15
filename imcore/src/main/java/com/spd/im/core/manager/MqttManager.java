package com.spd.im.core.manager;

import android.content.Context;

import com.spd.im.core.instance.ImSingleton;
import com.spd.im.core.listener.ILoginCallback;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


/**
 * @author :Reginer on  2020/3/16 10:38.
 * 联系方式:QQ:282921012
 * 功能描述:mqtt管理类
 */
public class MqttManager {
    private MqttAndroidClient mqttAndroidClient;

    private MqttManager() {
    }

    private static final ImSingleton<MqttManager> INSTANCE = new ImSingleton<MqttManager>() {

        @Override
        protected MqttManager create() {
            return new MqttManager();
        }
    };

    public static MqttManager getInstance() {
        return INSTANCE.get();
    }


    /**
     * 连接服务器
     */
    public void connect(Context context, String username, MqttCallbackExtended mqttCallbackExtended, final ILoginCallback iLoginCallback) {
        if (mqttAndroidClient == null) {
            mqttAndroidClient = new MqttAndroidClient(context.getApplicationContext(), "tcp://mqtt.speedata.cn:1883", username);
            mqttAndroidClient.setCallback(mqttCallbackExtended);
        }
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setUserName("mdm");
        mqttConnectOptions.setPassword("mdm_speedata".toCharArray());
        try {
            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    disconnectedBufferOptions.setBufferEnabled(true);
                    disconnectedBufferOptions.setBufferSize(100);
                    disconnectedBufferOptions.setPersistBuffer(false);
                    disconnectedBufferOptions.setDeleteOldestMessages(false);
                    mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);
                    iLoginCallback.onSuccess();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    iLoginCallback.onFailed(new Exception(exception));
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅消息
     *
     * @param topic 订阅消息的主题
     */
    public void subscribeMsg(String[] topic, int[] qos) {
        try {
            mqttAndroidClient.subscribe(topic, qos, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 发布消息
     *
     * @param topic 发布消息主题
     * @param msg   消息体
     */
    public void publish(String topic, String msg) {
        try {
            MqttMessage message = new MqttMessage();
            message.setPayload(msg.getBytes());
            mqttAndroidClient.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    /**
     * 断开链接
     */
    public void disconnect() {
        if (mqttAndroidClient != null) {
            try {
                mqttAndroidClient.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放资源
     */
    public void release() {
        if (mqttAndroidClient != null) {
            mqttAndroidClient.unregisterResources();
            mqttAndroidClient = null;
        }
    }

    /**
     * 判断服务是否连接
     *
     * @return 是否连接
     */
    public boolean isConnected() {
        if (mqttAndroidClient != null) {
            return mqttAndroidClient.isConnected();
        }
        return false;
    }
}