package com.daren.chen.dahua.api.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 14:19
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class DecoderUnitDto implements Serializable {

    /**
     *
     */
    private String decodeMode;

    /**
     *
     */
    private int conbineStatus;
}
