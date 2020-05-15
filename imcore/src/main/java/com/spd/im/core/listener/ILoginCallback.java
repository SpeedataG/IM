package com.spd.im.core.listener;

import android.annotation.NonNull;

/**
 * @author :Reginer on  2020/5/15 16:46.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public interface ILoginCallback {
    void onSuccess();

    void onFailed(@NonNull Exception e);
}
