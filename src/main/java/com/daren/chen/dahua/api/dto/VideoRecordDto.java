package com.daren.chen.dahua.api.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 16:13
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class VideoRecordDto implements Serializable {

    /**
     * 录像位置
     */
    private String location;
    /**
     * 录像文件名称
     */
    private String file;
    /**
     * 录像起始时间，UTC0时区时间，格式为YYYYMMDDTHHmmssZ
     */
    private String beginTime;
    /**
     * 录像结束时间，UTC0时区时间，格式为YYYYMMDDTHHmmssZ
     */
    private String endTime;
    /**
     * 录像类型
     */
    private String recordType;
    /**
     * 录像子类型
     */
    private String recordSubType;

    /**
     * 码流类型 main 主码流 extra1 辅码流1
     */
    private String videoStream;
    /**
     * 文件大小，单位KB
     */
    private int size;
    /**
     * 是否被锁定,设备录像不会被锁定
     */
    private boolean locked;
}
