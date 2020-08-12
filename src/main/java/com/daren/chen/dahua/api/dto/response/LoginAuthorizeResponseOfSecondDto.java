package com.daren.chen.dahua.api.dto.response;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 第二次 登录 返回的参数
 *
 * @Description: url: POST /videoService/accounts/authorize
 * @author: chendaren
 * @CreateDate: 2020/8/5 14:17
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class LoginAuthorizeResponseOfSecondDto implements Serializable {

    /**
     * 类型int。有效时间，由服务端指定，单位秒，建议3/4 duration时就进行会话更新
     */
    private Integer duration;

    /**
     * 返回登陆令牌，令牌字符串由服务端发布。之后的其它请求，在HTTP头的 X-Subject-Token: 带上这个令牌进行鉴权
     */
    private String token;

    /**
     * 创建该会话的用户名。
     */
    private String userName;

    /**
     * 用户名对应的用户Id，如果需要对该用户信息进行修改，使用用户Id。
     */
    private String userId;

    /**
     * 上次登陆的IP。
     */
    private String lastLoginIp;

    /**
     * 视频云版本
     */
    private String version;
}
