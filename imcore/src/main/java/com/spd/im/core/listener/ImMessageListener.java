package com.spd.im.core.listener;

import com.spd.im.core.entity.ImEntity;

/**
 * @author :Reginer on  2020/5/15 14:50.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public interface ImMessageListener {
    void onMessageReceived(ImEntity message);
}
