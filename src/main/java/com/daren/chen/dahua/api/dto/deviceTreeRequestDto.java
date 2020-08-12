package com.daren.chen.dahua.api.dto;

import java.io.Serializable;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 15:41
 */
public class deviceTreeRequestDto extends PageDato implements Serializable {

    /**
     * 必填。要查询组织的惟一编码。
     */
    private String id;

    /**
     * 必填。固定为1,表示组织
     */
    private Integer nodeType;
    /**
     * 必填。检索类型，值不同返回的内容不同。 查询设备"01;1;ALL"。查询设备和通 道"01;1;ALL;ALL"。仅查询通道"01;0;ALL;ALL"。
     */
    private String typeCode;
}
