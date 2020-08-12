package com.daren.chen.dahua.api.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 9:45
 */
public abstract class BaseService {

    protected Map<String, String> initialBasicHeader(Map<String, String> headers, String token) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        // headers.put("x-ca-timestamp", String.valueOf((new Date()).getTime()));
        // headers.put("x-ca-nonce", UUID.randomUUID().toString());
        headers.put("X-Subject-Token", token);
        return headers;
    }

}
