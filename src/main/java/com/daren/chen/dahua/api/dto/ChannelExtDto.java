package com.daren.chen.dahua.api.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 14:22
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class ChannelExtDto implements Serializable {
    private String roadway;
    private String cartMaxSpeed;
    private String cartMinSpeed;
    private String dollyMaxSpeed;
    private String dollyMinSpeed;
    private String direction;
    private String gbCode;
    private String db33Code;
    private String keyCode;
}
