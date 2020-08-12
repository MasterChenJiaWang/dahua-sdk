package com.daren.chen.dahua.api.dto.request;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 9:39
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class TokenLogoutRequestDto implements Serializable {

    private String token;

}
