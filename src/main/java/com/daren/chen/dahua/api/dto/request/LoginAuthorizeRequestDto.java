package com.daren.chen.dahua.api.dto.request;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description: url: POST /videoService/accounts/authorize
 * @author: chendaren
 * @CreateDate: 2020/8/5 14:17
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class LoginAuthorizeRequestDto implements Serializable {

    /**
     * 必填。用户名,最长32字节。
     */

    private String userName;

    /**
     * 明文
     */
    private String password;
    /**
     * 必填。客户端类型。固定填 winpc 。
     */
    private String clientType = "winpc";

    /**
     * 选填。客户端的ip地址，用于日志审计。
     */
    private String ipAddress;

    // ..............................第二次请求需要带上的参数

    /**
     * 必填。根据签名计算方法得到的签名值。
     */
    private String signature;

    /**
     * 必填。随机密钥种子。
     */
    private String randomKey;

    /**
     * 必填。加密算法。
     */
    private String encryptType;
}
