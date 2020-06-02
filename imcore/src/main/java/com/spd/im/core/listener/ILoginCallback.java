package com.spd.im.core.listener;


import androidx.annotation.NonNull;

/**
 * @author :Reginer on  2020/5/15 16:46.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public interface ILoginCallback {
    /**
     * 登录成功
     */
    void onSuccess();

    /**
     * 登录失败
     *
     * @param e 错误信息
     */
    void onFailed(@NonNull Exception e);
}
