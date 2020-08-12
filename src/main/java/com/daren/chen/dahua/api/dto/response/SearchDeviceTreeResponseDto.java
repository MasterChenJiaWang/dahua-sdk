package com.daren.chen.dahua.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.daren.chen.dahua.api.dto.DeviceInfoDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 17:20
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class SearchDeviceTreeResponseDto implements Serializable {

    /**
     *
     */
    private int totalCount;

    /**
     *
     */
    private List<DeviceInfoDto> results;
}
