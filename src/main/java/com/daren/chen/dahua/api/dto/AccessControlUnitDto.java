package com.daren.chen.dahua.api.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 14:10
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class AccessControlUnitDto implements Serializable {

    /**
     *
     */
    private int channelsCount;
    /**
     *
     */
    private List<ChannelDto> channels;
}
