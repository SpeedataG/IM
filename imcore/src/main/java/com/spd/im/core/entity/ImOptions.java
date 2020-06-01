package com.spd.im.core.entity;

import com.spd.im.core.net.ImUrls;

/**
 * @author :Reginer in  2020/6/1 9:38.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public class ImOptions {
    private String host = ImUrls.DEFAULT_HOST;
    private int port = ImUrls.DEFAULT_PORT;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


}
