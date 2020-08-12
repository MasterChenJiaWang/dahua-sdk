package com.daren.chen.dahua.api.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 13:48
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class ChannelDto implements Serializable {

    private String channelCode;
    private String channelId;
    private String channelName;
    private int channelSeq;
    private String deviceCode;
    private int isEnabled;
    private String orgCode;

    private int gatherRadius;
    private String carNum;
    private String metroLine;
    private String metroVehicle;
    private String metroCarriageSeq;
    private int mapId;
    private String macAddr;
    private int devAuth;
    private int passwordCrack;
    private String securityVendor;
    private int uploadInterval;
    private String placeCode;
    private String memo;
    private int isOnline;
    private String deviceLocation;
    private int macDeviceType;

    private String channelType;
    private String channelRemoteType;
    private String cameraFunctions;
    private String cameraType;
    private String gpsX;
    private String gpsY;
    private String pcFlag;

    private ChannelExtDto channelExt;
}
