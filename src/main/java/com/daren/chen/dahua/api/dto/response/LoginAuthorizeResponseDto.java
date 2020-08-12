package com.daren.chen.dahua.api.dto.response;

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
public class LoginAuthorizeResponseDto implements Serializable {

    /**
     * 域信息，加密过程使用
     */
    private String realm;
    /**
     * 随机密钥种子。
     */
    private String randomKey;
    /**
     * 加密算法
     */
    private String encryptType;

    /**
     * 使用哪种加密流程。没有该字段，按通通用用加加密密流流程程计算，"simple"表示按简简易易加加密密流流程程计 算
     */
    private String method;

    //

}
