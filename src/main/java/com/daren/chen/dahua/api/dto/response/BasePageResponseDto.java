package com.daren.chen.dahua.api.dto.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 15:45
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class BasePageResponseDto<T> implements Serializable {

    /**
     *
     */
    private int totalCount;

    /**
     *
     */
    private int nextPage;

    /**
     *
     */
    private List<T> results;
}
