package com.spd.im.core;

import android.content.Context;


import com.spd.im.core.instance.ImSingleton;
import com.spd.im.core.listener.ILoginCallback;
import com.spd.im.core.manager.ImChatManager;

/**
 * @author :Reginer on  2020/5/15 14:41.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public class ImClient {
    private Context mContext;
    private final ImChatManager imChatManager = new ImChatManager();
    private static final ImSingleton<ImClient> INSTANCE = new ImSingleton<ImClient>() {
        @Override
        protected ImClient create() {
            return new ImClient();
        }
    };

    public static ImClient getInstance() {
        return INSTANCE.get();
    }

    public void init(Context context) {
        mContext = context;
    }

    public ImChatManager getChatManager() {
        return imChatManager;
    }

    public void login(String username, String password, ILoginCallback iLoginCallback) {
        imChatManager.connect(mContext, username, iLoginCallback);
    }

    public void logout() {
        imChatManager.logOut();
    }
}
