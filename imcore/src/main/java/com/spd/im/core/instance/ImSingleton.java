package com.spd.im.core.instance;


/**
 * @author :Reginer on  2020/5/15 14:42.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public abstract class ImSingleton<T> {
    private T mInstance;

    protected abstract T create();

    public final T get() {
        synchronized (this) {
            if (mInstance == null) {
                mInstance = create();
            }
            return mInstance;
        }
    }
}