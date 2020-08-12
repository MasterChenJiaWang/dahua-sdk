package com.daren.chen.dahua.api.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpStatus;

import com.daren.chen.dahua.api.common.Method;
import com.daren.chen.dahua.api.common.UrlConstants;
import com.daren.chen.dahua.api.dto.DeviceInfoDto;
import com.daren.chen.dahua.api.dto.DeviceTreeDto;
import com.daren.chen.dahua.api.dto.SearchDeviceTreeQueryParam;
import com.daren.chen.dahua.api.dto.request.DeviceTreeRequestDto;
import com.daren.chen.dahua.api.dto.request.VideoRecordUrlQueryDto;
import com.daren.chen.dahua.api.dto.response.BasePageResponseDto;
import com.daren.chen.dahua.api.dto.response.SearchDeviceTreeResponseDto;
import com.daren.chen.dahua.api.dto.response.VideoRecordResponseDto;
import com.daren.chen.dahua.api.utils.Client;
import com.daren.chen.dahua.api.utils.Environment;
import com.daren.chen.dahua.api.utils.RequestDto;
import com.daren.chen.dahua.api.utils.ResponseDto;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 16:52
 */
public class DevicesManagerService extends BaseService {
    /**
     * 通过分级获取组织接口得到组织编码后，就可以查询这个组织下的设备和通道信息。
     *
     * @param deviceTreeRequestDto
     * @return
     */
    public Optional<BasePageResponseDto<DeviceTreeDto>> getDeviceTree(DeviceTreeRequestDto deviceTreeRequestDto)
        throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setMethod(Method.GET);
        requestDto.setHost(Environment.getHost());
        requestDto.setPath(UrlConstants.QUERY_DEVICE_TREE_URL);
        requestDto.setAppKey(Environment.getAppKey());
        requestDto.setAppSecret(Environment.getAppSecret());
        requestDto.setHeaders(initialBasicHeader(new HashMap<>(4), Environment.getToken()));
        requestDto.setQuerys((Map<String, String>)JSONUtil.toBean(JSONUtil.toJsonStr(deviceTreeRequestDto), Map.class));
        ResponseDto responseDto = Client.execute(requestDto);
        // !=200
        if (HttpStatus.SC_OK != responseDto.getStatusCode()) {
            throw new RuntimeException(responseDto.getErrorMessage());
        }
        return Optional.ofNullable(
            JSONUtil.toBean(responseDto.getBody(), new TypeReference<BasePageResponseDto<DeviceTreeDto>>() {}, false));
    }

    /**
     * 获取设备信息,可获取除密码字段之外的所有字段。
     *
     * @param deviceCode
     * @return
     * @throws Exception
     */
    public Optional<DeviceInfoDto> getDeviceInfoByCode(String deviceCode) throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setMethod(Method.GET);
        requestDto.setHost(Environment.getHost());
        requestDto.setPath(UrlConstants.FIND_DEVICE_INFO_URL + deviceCode);
        requestDto.setAppKey(Environment.getAppKey());
        requestDto.setAppSecret(Environment.getAppSecret());
        requestDto.setHeaders(initialBasicHeader(new HashMap<>(4), Environment.getToken()));
        ResponseDto responseDto = Client.execute(requestDto);
        // !=200
        if (HttpStatus.SC_OK != responseDto.getStatusCode()) {
            throw new RuntimeException(responseDto.getErrorMessage());
        }
        return Optional.ofNullable(JSONUtil.toBean(responseDto.getBody(), DeviceInfoDto.class));
    }

    /**
     *
     * @param searchDeviceTreeQueryParam
     * @throws Exception
     */
    public Optional<SearchDeviceTreeResponseDto> searchDeviceTree(SearchDeviceTreeQueryParam searchDeviceTreeQueryParam)
        throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setMethod(Method.GET);
        requestDto.setHost(Environment.getHost());
        requestDto.setPath(UrlConstants.SEARCH_DEVICE_TREE_URL);
        requestDto.setAppKey(Environment.getAppKey());
        requestDto.setAppSecret(Environment.getAppSecret());
        requestDto.setHeaders(initialBasicHeader(new HashMap<>(4), Environment.getToken()));
        requestDto.setQuerys(JSONUtil.toBean(JSONUtil.toJsonStr(searchDeviceTreeQueryParam),
            new TypeReference<Map<String, String>>() {}, false));
        ResponseDto responseDto = Client.execute(requestDto);
        // !=200
        if (HttpStatus.SC_OK != responseDto.getStatusCode()) {
            throw new RuntimeException(responseDto.getErrorMessage());
        }
        return Optional.ofNullable(JSONUtil.toBean(responseDto.getBody(), SearchDeviceTreeResponseDto.class));
    }

    /**
     * 获获取取实实时时监监视视URI
     *
     * @param channelId
     *            必填。通道编码。
     * @param subType
     *            选填。码流类型，0:主码流、1:辅流1、2:辅流2。默认为0主码流。
     * @param scheme
     *            选填。协议类型，支持RTSP、FLV_HTTP、HLS三种，默认RTSP。
     */
    public Optional<String> videoUrl(String channelId, String subType, String scheme) throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setMethod(Method.GET);
        requestDto.setHost(Environment.getHost());
        requestDto.setPath(UrlConstants.FIND_VIDEO_URL);
        requestDto.setAppKey(Environment.getAppKey());
        requestDto.setAppSecret(Environment.getAppSecret());
        requestDto.setHeaders(initialBasicHeader(new HashMap<>(4), Environment.getToken()));
        HashMap<String, String> query = new HashMap<>(8);
        query.put("channelId", channelId);
        query.put("subType", subType);
        query.put("scheme", scheme);
        requestDto.setQuerys(query);
        ResponseDto responseDto = Client.execute(requestDto);
        // !=200
        if (HttpStatus.SC_OK != responseDto.getStatusCode()) {
            throw new RuntimeException(responseDto.getErrorMessage());
        }
        Map map = JSONUtil.toBean(responseDto.getBody(), Map.class);
        String url = map.get("uri") == null ? "" : map.get("uri").toString();
        if (StrUtil.isNotBlank(url)) {
            return Optional.ofNullable(url);
        }
        throw new RuntimeException("获取url为空!");
    }

    /**
     * 按时间段查询录像的分布情况，如果要回放，再调用获取回放URI。
     *
     * @param channelCode
     *            必填。通道ID，当前只支持单通道查询。
     * @param beginTime
     *            必填。开始时间，UTC0时区时间，格式为YYYYMMDDTHHmmssZ。
     * @param endTime
     *            必填。结束时间，UTC0时区时间，格式为YYYYMMDDTHHmmssZ。
     */
    public Optional<VideoRecordResponseDto> findVideoRecordList(String channelCode, String beginTime, String endTime)
        throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setMethod(Method.GET);
        requestDto.setHost(Environment.getHost());
        requestDto.setPath(UrlConstants.FIND_VIDEO_RECORDS_LIST_URL);
        requestDto.setAppKey(Environment.getAppKey());
        requestDto.setAppSecret(Environment.getAppSecret());
        requestDto.setHeaders(initialBasicHeader(new HashMap<>(4), Environment.getToken()));
        HashMap<String, String> query = new HashMap<>(8);
        query.put("channelCode", channelCode);
        query.put("beginTime", beginTime);
        query.put("endTime", endTime);
        requestDto.setQuerys(query);
        ResponseDto responseDto = Client.execute(requestDto);
        // !=200
        if (HttpStatus.SC_OK != responseDto.getStatusCode()) {
            throw new RuntimeException(responseDto.getErrorMessage());
        }
        return Optional.ofNullable(JSONUtil.toBean(responseDto.getBody(), VideoRecordResponseDto.class));
    }

    /**
     * 获取录像文件的uri，用于录像回放。支持RTSP和HLS两种传输方式，两种方式支持不同的检索条件。
     *
     * 1. RTSP：支持 按按文文件件 和 按按时时间间 回放。 2. HLS：只支持 按按文文件件 回放。 注意:HLS方式，如果不指定文件名，也会返回uri地址，但不能播放。另外RTSP的按时间回放一定要确认这段时间内有视
     * 频，否则即使返回url也不能看到视频。
     *
     * @param videoRecordUrlQueryDto
     * @return
     */
    public Optional<String> findVideoRecordUrl(VideoRecordUrlQueryDto videoRecordUrlQueryDto) throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setMethod(Method.GET);
        requestDto.setHost(Environment.getHost());
        requestDto.setPath(UrlConstants.FIND_VIDEO_PLAYBACK_URL);
        requestDto.setAppKey(Environment.getAppKey());
        requestDto.setAppSecret(Environment.getAppSecret());
        requestDto.setHeaders(initialBasicHeader(new HashMap<>(4), Environment.getToken()));
        requestDto.setQuerys(JSONUtil.toBean(JSONUtil.toJsonStr(videoRecordUrlQueryDto),
            new TypeReference<Map<String, String>>() {}, false));
        ResponseDto responseDto = Client.execute(requestDto);
        // !=200
        if (HttpStatus.SC_OK != responseDto.getStatusCode()) {
            throw new RuntimeException(responseDto.getErrorMessage());
        }
        Map map = JSONUtil.toBean(responseDto.getBody(), Map.class);
        String url = map.get("uri") == null ? "" : map.get("uri").toString();
        if (StrUtil.isNotBlank(url)) {
            return Optional.ofNullable(url);
        }
        throw new RuntimeException("获取url为空!");
    }
}
