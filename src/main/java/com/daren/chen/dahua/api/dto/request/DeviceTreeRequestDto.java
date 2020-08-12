package com.daren.chen.dahua.api.dto.request;

import java.io.Serializable;

import com.daren.chen.dahua.api.dto.PageDato;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 11:24
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class DeviceTreeRequestDto extends PageDato implements Serializable {

    /**
     *
     */
    private String id;

    /**
     *
     */
    private int nodeType;

    /**
     *
     */
    private String typeCode;

}
