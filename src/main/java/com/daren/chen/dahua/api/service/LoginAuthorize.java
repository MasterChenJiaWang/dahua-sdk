package com.daren.chen.dahua.api.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpStatus;

import com.daren.chen.dahua.api.common.Method;
import com.daren.chen.dahua.api.common.UrlConstants;
import com.daren.chen.dahua.api.dto.request.LoginAuthorizeRequestDto;
import com.daren.chen.dahua.api.dto.request.TokenKeepaliveRequestDto;
import com.daren.chen.dahua.api.dto.request.TokenLogoutRequestDto;
import com.daren.chen.dahua.api.dto.response.BaseResponseDto;
import com.daren.chen.dahua.api.dto.response.LoginAuthorizeResponseDto;
import com.daren.chen.dahua.api.dto.response.LoginAuthorizeResponseOfSecondDto;
import com.daren.chen.dahua.api.utils.Client;
import com.daren.chen.dahua.api.utils.Environment;
import com.daren.chen.dahua.api.utils.RequestDto;
import com.daren.chen.dahua.api.utils.ResponseDto;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/5 14:26
 */
public class LoginAuthorize extends BaseService {

    /**
     * 缓存 token 是否过期
     */
    private static final TimedCache<String, String> TIMED_CACHE = CacheUtil.newTimedCache(100000);

    /**
     * 缓存 token
     */
    private static final Map<String, String> TOKEN_CACHE_MAP = new ConcurrentHashMap<>(4);
    /**
     * 缓存token失效时间
     */
    private static final Map<String, Integer> TOKEN_TIME_CACHE_MAP = new ConcurrentHashMap<>(4);

    /**
     *
     * @param loginAuthorizeRequestDto
     * @return 返回token
     * @throws Exception
     */
    public Optional<String> login(LoginAuthorizeRequestDto loginAuthorizeRequestDto) throws Exception {
        // 第一次请求
        LoginAuthorizeResponseDto loginAuthorizeResponseDto = fristLogin(loginAuthorizeRequestDto);
        loginAuthorizeRequestDto.setRandomKey(loginAuthorizeResponseDto.getRandomKey());
        loginAuthorizeRequestDto.setEncryptType(loginAuthorizeResponseDto.getEncryptType());
        // 获取加密流程签名
        String signature = signature(loginAuthorizeRequestDto.getUserName(), loginAuthorizeRequestDto.getPassword(),
            loginAuthorizeResponseDto.getRealm(), loginAuthorizeResponseDto.getRandomKey());
        loginAuthorizeRequestDto.setSignature(signature);
        // 第二次请求
        LoginAuthorizeResponseOfSecondDto loginAuthorizeResponseOfSecondDto = secondLogin(loginAuthorizeRequestDto);
        String token = loginAuthorizeResponseOfSecondDto.getToken();
        if (StrUtil.isNotBlank(token)) {
            Integer duration = loginAuthorizeResponseOfSecondDto.getDuration();
            addCache(token, duration);
        }
        return Optional.ofNullable(token);
    }

    public Optional<String> getToken() throws Exception {
        String appKey = Environment.getAppKey();
        String token = TOKEN_CACHE_MAP.getOrDefault(appKey, "");
        tokenKeepalive(token);
        token = TOKEN_CACHE_MAP.getOrDefault(appKey, "");
        return Optional.ofNullable(token);
    }

    /**
     *
     * @throws Exception
     */
    public void tokenKeepalive(String token) throws Exception {
        if (StrUtil.isBlank(token)) {
            clear();
            initLogin();
            return;
        }
        String appKey = Environment.getAppKey();
        String token2 = TIMED_CACHE.get(appKey, false);
        if (StrUtil.isNotBlank(token2)) {
            if (!token2.equals(token)) {
                clear();
                initLogin();
            }
            return;
        }
        TokenKeepaliveRequestDto tokenKeepaliveRequestDto = new TokenKeepaliveRequestDto();
        tokenKeepaliveRequestDto.setToken(token);
        //
        BaseResponseDto baseResponseDto = tokenKeepalive(tokenKeepaliveRequestDto);
        if (baseResponseDto == null) {
            throw new RuntimeException("保活失败!");
        }
        if (HttpStatus.SC_OK == baseResponseDto.getCode()) {
            Integer duration = TOKEN_TIME_CACHE_MAP.get(appKey);
            TIMED_CACHE.put(appKey, token, duration);
            return;
        }
        // 清空token
        Environment.setToken(null);
        clear();
        throw new RuntimeException("保活失败:" + baseResponseDto.getMessage());
    }

    /**
     *
     * @param token
     * @param duration
     */
    private void addCache(String token, Integer duration) {
        // 取过期时间3/4
        int v = (int)(duration * 0.75 * 1000);
        TIMED_CACHE.put(Environment.getAppKey(), token, v);
        TOKEN_CACHE_MAP.put(Environment.getAppKey(), token);
        TOKEN_TIME_CACHE_MAP.put(Environment.getAppKey(), v);
    }

    /**
     *
     */
    private void clear() {
        String appKey = Environment.getAppKey();
        TIMED_CACHE.remove(appKey);
        TOKEN_CACHE_MAP.remove(appKey);
        TOKEN_TIME_CACHE_MAP.remove(appKey);
    }

    /**
     *
     */
    public void logout() throws Exception {
        String token = TOKEN_CACHE_MAP.getOrDefault(Environment.getAppKey(), "");
        if (StrUtil.isBlank(token)) {
            clear();
            return;
        }
        TokenLogoutRequestDto tokenLogoutRequestDto = new TokenLogoutRequestDto();
        tokenLogoutRequestDto.setToken(token);
        BaseResponseDto baseResponseDto = tokenLogout(tokenLogoutRequestDto);
        if (HttpStatus.SC_OK == baseResponseDto.getCode()) {
            clear();
            return;
        }
        throw new RuntimeException("销毁会话失败!");
    }

    /**
     *
     * @param loginAuthorizeRequestDto
     * @return
     * @throws Exception
     */
    private LoginAuthorizeResponseDto fristLogin(LoginAuthorizeRequestDto loginAuthorizeRequestDto) throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setMethod(Method.POST_STRING);
        requestDto.setHost(Environment.getHost());
        requestDto.setPath(UrlConstants.LOGIN_AUTHORIZE_URL);
        // requestDto.setPath(Environment.get());
        requestDto.setAppKey(Environment.getAppKey());
        requestDto.setAppSecret(Environment.getAppSecret());
        requestDto.setStringBody(JSONUtil.toJsonStr(loginAuthorizeRequestDto));
        ResponseDto responseDto = Client.execute(requestDto);
        // 401
        if (HttpStatus.SC_UNAUTHORIZED != responseDto.getStatusCode()) {
            throw new RuntimeException(responseDto.getErrorMessage());
        }
        return JSONUtil.toBean(responseDto.getBody(), LoginAuthorizeResponseDto.class);
    }

    /**
     *
     * @param loginAuthorizeRequestDto
     * @throws Exception
     */
    private LoginAuthorizeResponseOfSecondDto secondLogin(LoginAuthorizeRequestDto loginAuthorizeRequestDto)
        throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setMethod(Method.POST_STRING);
        requestDto.setHost(Environment.getHost());
        requestDto.setPath(UrlConstants.LOGIN_AUTHORIZE_URL);
        requestDto.setAppKey(Environment.getAppKey());
        requestDto.setAppSecret(Environment.getAppSecret());
        requestDto.setStringBody(JSONUtil.toJsonStr(loginAuthorizeRequestDto));
        ResponseDto responseDto = Client.execute(requestDto);
        // 200
        if (HttpStatus.SC_OK != responseDto.getStatusCode()) {
            throw new RuntimeException(responseDto.getErrorMessage());
        }
        return JSONUtil.toBean(responseDto.getBody(), LoginAuthorizeResponseOfSecondDto.class);
    }

    private void initLogin() throws Exception {
        LoginAuthorizeRequestDto loginAuthorizeRequestDto = new LoginAuthorizeRequestDto();
        loginAuthorizeRequestDto.setUserName(Environment.getUserName());
        loginAuthorizeRequestDto.setPassword(Environment.getPassword());
        login(loginAuthorizeRequestDto);
    }

    /**
     *
     * @param tokenLogoutRequestDto
     * @return
     * @throws Exception
     */
    private BaseResponseDto tokenLogout(TokenLogoutRequestDto tokenLogoutRequestDto) throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setMethod(Method.POST_STRING);
        requestDto.setHost(Environment.getHost());
        requestDto.setPath(UrlConstants.LOGIN_TOKEN_KEEPALIVE_URL);
        requestDto.setAppKey(Environment.getAppKey());
        requestDto.setHeaders(initialBasicHeader(new HashMap<>(4), tokenLogoutRequestDto.getToken()));
        requestDto.setAppSecret(Environment.getAppSecret());
        requestDto.setStringBody(JSONUtil.toJsonStr(tokenLogoutRequestDto));
        ResponseDto responseDto = Client.execute(requestDto);
        // 200
        if (HttpStatus.SC_OK != responseDto.getStatusCode()) {
            throw new RuntimeException(responseDto.getErrorMessage());
        }
        return JSONUtil.toBean(responseDto.getBody(), BaseResponseDto.class);
    }

    /**
     *
     * @param tokenKeepaliveRequestDto
     * @return
     * @throws Exception
     */
    private BaseResponseDto tokenKeepalive(TokenKeepaliveRequestDto tokenKeepaliveRequestDto) throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setMethod(Method.POST_STRING);
        requestDto.setHost(Environment.getHost());
        requestDto.setPath(UrlConstants.LOGIN_TOKEN_KEEPALIVE_URL);
        requestDto.setAppKey(Environment.getAppKey());
        requestDto.setHeaders(initialBasicHeader(new HashMap<>(4), tokenKeepaliveRequestDto.getToken()));
        requestDto.setAppSecret(Environment.getAppSecret());
        requestDto.setStringBody(JSONUtil.toJsonStr(tokenKeepaliveRequestDto));
        ResponseDto responseDto = Client.execute(requestDto);
        // 200
        if (HttpStatus.SC_OK != responseDto.getStatusCode()) {
            throw new RuntimeException(responseDto.getErrorMessage());
        }
        return JSONUtil.toBean(responseDto.getBody(), BaseResponseDto.class);
    }

    /**
     *
     * @param userName
     * @param password
     * @param realm
     * @param randomKey
     * @return
     */
    private String signature(String userName, String password, String realm, String randomKey) {
        String signature = SecureUtil.md5(password);
        signature = SecureUtil.md5(userName + signature);
        signature = SecureUtil.md5(signature);
        signature = SecureUtil.md5(userName + ":" + realm + ":" + signature);
        signature = SecureUtil.md5(signature + ":" + randomKey);
        return signature;
    }

}
