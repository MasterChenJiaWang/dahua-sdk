package com.daren.chen.dahua.api.common;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/5 14:28
 */
public class UrlConstants {

    /**
     *
     */
    public static final String LOGIN_AUTHORIZE_URL = "/videoService/accounts/authorize";
    /**
     *
     */
    public static final String LOGIN_TOKEN_KEEPALIVE_URL = "/videoService/accounts/token/keepalive";
    /**
     *
     */
    public static final String LOGIN_LOGOUT_URL = "/videoService/accounts/unauthorize";
    /**
     * 分分级级获获取取设设备备通通道 通过分级获取组织接口得到组织编码后，就可以查询这个组织下的设备和通道信息。
     */
    public static final String QUERY_DEVICE_TREE_URL = "/videoService/devicesManager/deviceTree?";
    /**
     * 获取设备信息,可获取除密码字段之外的所有字段。
     */
    public static final String FIND_DEVICE_INFO_URL = "/videoService/devicesManager/devices/";
    /**
     *
     */
    public static final String SEARCH_DEVICE_TREE_URL = "/common/fcs/deviceTree/search?";

    /**
     * 获取一个视频通道播放URI资源，URI中带有token信息，流媒体服务端通过token进行鉴权。
     */
    public static final String FIND_VIDEO_URL = "/videoService/realmonitor/uri?";
    /**
     * 按时间段查询录像的分布情况，如果要回放，再调用获取回放URI
     */
    public static final String FIND_VIDEO_RECORDS_LIST_URL = "/videoService/record/records?";

    /**
     * 获取录像文件的uri，用于录像回放。支持RTSP和HLS两种传输方式，两种方式支持不同的检索条件
     */
    public static final String FIND_VIDEO_PLAYBACK_URL = "/videoService/playback/uri?";
}
