package com.daren.chen.dahua.api.utils;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/5 15:30
 */
public class Client {

    public static ResponseDto execute(RequestDto requestDto) throws Exception {
        switch (requestDto.getMethod()) {
            case GET:
                return HttpUtil.httpGet(requestDto);
            case POST_STRING:
                return HttpUtil.httpPost(requestDto);
            case POST_STRING_RESPONSE:
                return HttpUtil.httpPost(requestDto);
            // case POST_FORM:
            // return HttpUtils.httpPost(requestDto.getHost(), requestDto.getPath(), requestDto.getTimeout(),
            // requestDto.getHeaders(), requestDto.getQuerys(), requestDto.getBodys(),
            // requestDto.getSignHeaderPrefixList(), requestDto.getAppKey(), requestDto.getAppSecret());
            // case POST_FORM_RESPONSE:
            // return HttpUtils.httpImgPost(requestDto.getHost(), requestDto.getPath(), requestDto.getTimeout(),
            // requestDto.getHeaders(), requestDto.getQuerys(), requestDto.getBodys(),
            // requestDto.getSignHeaderPrefixList(), requestDto.getAppKey(), requestDto.getAppSecret());
            // case POST_STRING:
            // return HttpUtils.httpPost(requestDto.getHost(), requestDto.getPath(), requestDto.getTimeout(),
            // requestDto.getHeaders(), requestDto.getQuerys(), requestDto.getStringBody(),
            // requestDto.getSignHeaderPrefixList(), requestDto.getAppKey(), requestDto.getAppSecret());
            // case POST_STRING_RESPONSE:
            // return HttpUtils.httpImgPost(requestDto.getHost(), requestDto.getPath(), requestDto.getTimeout(),
            // requestDto.getHeaders(), requestDto.getQuerys(), requestDto.getStringBody(),
            // requestDto.getSignHeaderPrefixList(), requestDto.getAppKey(), requestDto.getAppSecret());
            // case POST_BYTES:
            // return HttpUtils.httpPost(requestDto.getHost(), requestDto.getPath(), requestDto.getTimeout(),
            // requestDto.getHeaders(), requestDto.getQuerys(), requestDto.getBytesBody(),
            // requestDto.getSignHeaderPrefixList(), requestDto.getAppKey(), requestDto.getAppSecret());
            // case PUT_STRING:
            // return HttpUtils.httpPut(requestDto.getHost(), requestDto.getPath(), requestDto.getTimeout(),
            // requestDto.getHeaders(), requestDto.getQuerys(), requestDto.getStringBody(),
            // requestDto.getSignHeaderPrefixList(), requestDto.getAppKey(), requestDto.getAppSecret());
            // case PUT_BYTES:
            // return HttpUtils.httpPut(requestDto.getHost(), requestDto.getPath(), requestDto.getTimeout(),
            // requestDto.getHeaders(), requestDto.getQuerys(), requestDto.getBytesBody(),
            // requestDto.getSignHeaderPrefixList(), requestDto.getAppKey(), requestDto.getAppSecret());
            // case DELETE:
            // return HttpUtils.httpDelete(requestDto.getHost(), requestDto.getPath(), requestDto.getTimeout(),
            // requestDto.getHeaders(), requestDto.getQuerys(), requestDto.getSignHeaderPrefixList(),
            // requestDto.getAppKey(), requestDto.getAppSecret());
            default:
                throw new IllegalArgumentException(String.format("unsupported method:%s", requestDto.getMethod()));
        }
    }
}
