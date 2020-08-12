package com.daren.chen.dahua.api.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import cn.hutool.core.util.StrUtil;

/**
 *
 *
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/5 15:37
 */
public class HttpUtil {

    /**
     *
     * @param requestDto
     * @return
     * @throws Exception
     */
    public static ResponseDto httpPost(RequestDto requestDto) throws Exception {
        Request request = Request.Post(requestDto.getHost());
        Map<String, String> headers = requestDto.getHeaders();
        if (headers != null) {
            headers.forEach(request::setHeader);
        }
        return request.connectTimeout(requestDto.getTimeout()).socketTimeout(requestDto.getTimeout())
            .bodyString(requestDto.getStringBody(), ContentType.APPLICATION_JSON).execute()
            .handleResponse(new HandleResponseImpl());
    }

    public static ResponseDto httpGet(RequestDto requestDto) throws Exception {
        Request request = Request.Get(initUrl(requestDto.getHost(), requestDto.getPath(), requestDto.getQuerys()));
        Map<String, String> headers = requestDto.getHeaders();
        if (headers != null) {
            headers.forEach(request::setHeader);
        }
        return request.connectTimeout(requestDto.getTimeout()).socketTimeout(requestDto.getTimeout()).bodyForm()
            .execute().handleResponse(new HandleResponseImpl());
    }

    /**
     *
     * @param host
     * @param path
     * @param querys
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String initUrl(String host, String path, Map<String, String> querys)
        throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StrUtil.isBlank(path)) {
            sbUrl.append(path);
        }

        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();

            for (Map.Entry<String, String> stringStringEntry : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StrUtil.isBlank(stringStringEntry.getKey()) && !StrUtil.isBlank((stringStringEntry.getValue()))) {
                    sbQuery.append(stringStringEntry.getValue());
                }
                if (!StrUtil.isBlank((stringStringEntry.getKey()))) {
                    sbQuery.append((stringStringEntry.getKey()));
                    if (!StrUtil.isBlank((stringStringEntry.getValue()))) {
                        sbQuery.append("=");
                        sbQuery
                            .append(URLEncoder.encode((stringStringEntry.getValue()), StandardCharsets.UTF_8.name()));
                    }
                }
            }

            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }

}
