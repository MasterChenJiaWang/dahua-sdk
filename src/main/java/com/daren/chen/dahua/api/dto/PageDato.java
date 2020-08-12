package com.daren.chen.dahua.api.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 11:26
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class PageDato implements Serializable {

    /**
     *
     */
    private int page = 1;

    /**
     *
     */
    private int pageSize = 10;
}
