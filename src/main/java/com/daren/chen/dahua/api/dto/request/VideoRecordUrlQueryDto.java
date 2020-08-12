package com.daren.chen.dahua.api.dto.request;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 16:42
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class VideoRecordUrlQueryDto implements Serializable {

    /**
     * 必填。视频通道编码。
     */
    String channelCode;
    /**
     * 选填。按文件回放需要，视频文件资源路径，可以通过录像查询的file字段获得，或者手动上 传视频文件。
     *
     */
    String resource;
    /**
     * 必填。按文件和按时间回放都需要，开始回放时间。时间格式:YYYYMMDDTHHmmssZ。
     */
    String beginTime;
    /**
     * 必填。按文件和按时间回放都需要，结束回放时间。时间格式:YYYYMMDDTHHmmssZ
     */
    String endTime;
    /**
     * 必填。录像存储位置。 cloud 平台录像 device 设备录像
     */
    String location;
    /**
     * 选填。协议类型，支持RTSP、HLS两种。默认RTSP。
     */
    String scheme;
    /**
     * 选填。有效时间，单位为秒，最长不超过10分钟，默认10分钟
     */
    String duration;
}
