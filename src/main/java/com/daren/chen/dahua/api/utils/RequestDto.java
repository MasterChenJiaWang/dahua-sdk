package com.daren.chen.dahua.api.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.daren.chen.dahua.api.common.Method;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/5 15:04
 */
@Data
@EqualsAndHashCode
public class RequestDto implements Serializable {

    /**
     *
     */
    private Method method;

    /**
     *
     */
    private String host;
    /**
     *
     */
    private String path;

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
    private int timeout = 20000;

    /**
     *
     */
    private Map<String, String> headers;

    /**
     *
     */
    private Map<String, String> querys;

    /**
     *
     */
    private Map<String, String> bodys;

    /**
     *
     */
    private String stringBody;

    /**
     *
     */
    private byte[] bytesBody;

    /**
     *
     */
    private List<String> signHeaderPrefixList;

    public RequestDto() {}

    public RequestDto(Method method, String host, String path, String appKey, String appSecret, int timeout) {
        this.method = method;
        this.host = host;
        this.path = path;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.timeout = timeout;
    }
}
