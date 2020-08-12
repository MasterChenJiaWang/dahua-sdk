package com.daren.chen.dahua.api.dto.response;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 9:43
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class BaseResponseDto implements Serializable {

    /**
     * 保活成功为200
     */
    private Integer code;

    /**
     * 提示消息
     */
    private String message;
}
