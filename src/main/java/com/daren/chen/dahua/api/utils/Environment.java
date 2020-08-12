package com.daren.chen.dahua.api.utils;

import com.daren.chen.dahua.api.dto.CustomizeArtemisConfig;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/3/23 8:38
 */
public class Environment {

    /**
     *
     */
    private static final ThreadLocal<CustomizeArtemisConfig> loginUserThreadLocal = new ThreadLocal<>();

    /**
     *
     * @return
     */
    public static CustomizeArtemisConfig getCurrentUser() {
        return loginUserThreadLocal.get();
    }

    /**
     *
     * @return
     */
    public static String getAppSecret() {
        CustomizeArtemisConfig user = loginUserThreadLocal.get();
        return user == null ? null : user.getAppSecret();
    }

    /**
     *
     * @return
     */
    public static String getHost() {
        CustomizeArtemisConfig customizeArtemisConfig = loginUserThreadLocal.get();
        return customizeArtemisConfig == null ? null : customizeArtemisConfig.getHost();
    }

    /**
     *
     * @return
     */
    public static String getAppKey() {
        CustomizeArtemisConfig customizeArtemisConfig = loginUserThreadLocal.get();
        return customizeArtemisConfig == null ? null : customizeArtemisConfig.getAppKey();
    }

    /**
     *
     * @return
     */
    public static String getToken() {
        CustomizeArtemisConfig customizeArtemisConfig = loginUserThreadLocal.get();
        return customizeArtemisConfig == null ? null : customizeArtemisConfig.getToken();
    }

    /**
     *
     * @return
     */
    public static String getUserName() {
        CustomizeArtemisConfig customizeArtemisConfig = loginUserThreadLocal.get();
        return customizeArtemisConfig == null ? null : customizeArtemisConfig.getUserName();
    }

    /**
     *
     * @return
     */
    public static String getPassword() {
        CustomizeArtemisConfig customizeArtemisConfig = loginUserThreadLocal.get();
        return customizeArtemisConfig == null ? null : customizeArtemisConfig.getPassword();
    }

    /**
     *
     * @return
     */
    public static void setToken(String token) {
        CustomizeArtemisConfig customizeArtemisConfig = loginUserThreadLocal.get();
        if (customizeArtemisConfig != null) {
            customizeArtemisConfig.setToken(token);
        }
    }

    /**
     *
     * @param customizeArtemisConfig
     */
    public static void setArtemisConfig(CustomizeArtemisConfig customizeArtemisConfig) {
        loginUserThreadLocal.set(customizeArtemisConfig);
    }

    /**
     *
     */
    public static void destroy() {
        loginUserThreadLocal.remove();
    }
}
