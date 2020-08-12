package com.daren.chen.dahua.api.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 13:34
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class DeviceInfoDto implements Serializable {

    private String deviceCode;
    private String deviceIp;
    private int devicePort;
    private String loginName;
    private String protocolType;
    private String addType;
    private String deviceType;
    private String deviceModel;
    private String deviceName;
    private String deviceSn;
    private String memo;
    private String registDeviceCode;
    private String deviceManufacturer;
    private String orgCode;
    private String orgName;
    private String stat;
    private String regionId;
    private String regionName;
    private String paasDeviceId;
    private String deviceCategory;
    private int domainId;
    private String deviceGateway;
    private EncoderUnitDto encoderUnit;
    private DecoderUnitDto decoderUnit;
    private BaseUnitdDto alarmInputUnit;
    private BaseUnitdDto alarmOutputUnit;
    private ScreenInputUnitDto screenInputUnit;
    private ScreenOutputUnitDto screenOutputUnit;
    private AccessControlUnitDto accessControlUnit;
    private DevAudioUnitDto devAudioUnit;
    private MacUnitDto macUnit;
    private RfidUnitDto rfidUnit;

}
