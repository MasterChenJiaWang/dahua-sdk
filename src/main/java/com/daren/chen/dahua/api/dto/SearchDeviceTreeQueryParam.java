package com.daren.chen.dahua.api.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 15:50
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class SearchDeviceTreeQueryParam implements Serializable {

    /**
     * 必填。模糊检索关键字，支持拼音搜索、多空格搜索，支持拼音首字母等，最长不超过 128字节默认不限。
     */
    private String keyWord;
    /**
     * 选填。排序类型,选填参数:deviceIp=设备ip，name=设备名称，type=设备类型
     */
    private String orderType;
    /**
     * 选填。排序方向,asc=升序，desc=降序。
     */
    private String direction;
    /**
     * ，选填。权限位，每位表示一种权限，共64种权限，参考字典表
     */
    private String rights;
    /**
     * 选填。 表示此级组织节点下的设备的检索类型格式
     */
    private String typeCode;

    /**
     * 选填。返回数据的格式，1：组织，2：设备，3：通道，默认：通道。
     */
    private String resultType;

    /**
     * 选填。表示获取的设备组织的类型（1:基本组织 参考字典表），默认为1
     */
    private String orgType;
    /**
     * 必填。是否需要设备节点（0，不需要设备节点，1:需要设备节点）。
     */
    private String showDev;
    /**
     * 选填。表示设备大类，用","可以组合多个，参考字典表。
     */
    private String category;
    /**
     * 选填。表示设备小类（现在不支持），用","可以组合多个，参考字典表。
     */
    private String model;
    /**
     * 选填。表示单元类型，用","可以组合多个，参考字典表。
     */
    private String unitType;
    /**
     * 选填。表示通道类型（现在不支持），用","可以组合多个，参考字典 表
     */
    private String channelType;

}
