package com.daren.chen.dahua.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/3/23 8:44
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CustomizeArtemisConfig {

    /**
     *
     */
    private String ip;

    /**
     *
     */
    private String port;
    /**
     *
     */
    private String host;
    /**
     *
     */
    private String appKey;
    /**
     *
     */
    private String appSecret;

    /**
     *
     */
    private String userName;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String token;

    public CustomizeArtemisConfig() {}

    public CustomizeArtemisConfig(String host, String appKey, String appSecret) {
        this.host = host;
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public CustomizeArtemisConfig(String ip, String port, String appKey, String appSecret) {
        this.ip = ip;
        this.port = port;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.host = ip + ":" + port;
    }

    public CustomizeArtemisConfig(String ip, String port, String host, String appKey, String appSecret, String token) {
        this.ip = ip;
        this.port = port;
        this.host = host;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.token = token;
    }

    public CustomizeArtemisConfig(String ip, String port, String host, String appKey, String appSecret, String userName,
        String password, String token) {
        this.ip = ip;
        this.port = port;
        this.host = host;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.userName = userName;
        this.password = password;
        this.token = token;
    }
}
