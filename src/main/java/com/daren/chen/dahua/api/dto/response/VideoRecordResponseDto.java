package com.daren.chen.dahua.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.daren.chen.dahua.api.dto.VideoRecordDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 16:13
 */
@NoArgsConstructor
@Data
public class VideoRecordResponseDto implements Serializable {

    /**
     * 通道序号。
     */
    private int channelSeq;
    /**
     * 回放模式。 0 支持按文件和按时间 1 只支持按文件 2 只支持按时间
     */
    private int playbackType;

    /**
     *
     */
    private List<VideoRecordDto> records;

}
