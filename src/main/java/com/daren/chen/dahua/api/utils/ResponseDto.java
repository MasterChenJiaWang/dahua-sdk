package com.daren.chen.dahua.api.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/5 15:07
 */
@Data
@EqualsAndHashCode
public class ResponseDto implements Serializable {

    /**
     *
     */
    private int statusCode;

    /**
     *
     */
    private String contentType;
    /**
     *
     */
    private String requestId;
    /**
     *
     */
    private String errorMessage;
    /**
     *
     */
    private Map<String, String> headers;
    /**
     *
     */
    private String body;
    /**
     *
     */
    private HttpResponse response;

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String getHeader(String key) {
        return null != this.headers ? (String)this.headers.get(key) : null;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setHeader(String key, String value) {
        if (null == this.headers) {
            this.headers = new HashMap<>(4);
        }

        this.headers.put(key, value);
    }

}
