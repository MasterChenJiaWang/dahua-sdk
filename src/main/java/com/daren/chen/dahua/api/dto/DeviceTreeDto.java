package com.daren.chen.dahua.api.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: chendaren
 * @CreateDate: 2020/8/10 11:33
 */
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class DeviceTreeDto implements Serializable {
    /**
     *
     */
    private int domainId;
    /**
     *
     */
    private String icon;
    /**
     *
     */
    private String id;

    /**
     *
     */
    private String memo;
    /**
     *
     */
    private String name;

    /**
     *
     */
    private int nodeType;

    /**
     *
     */
    private String orgCode;
    /**
     *
     */
    private String orgName;
    /**
     *
     */
    private String orgType;

    /**
     *
     */
    private boolean parent;

    /**
     *
     */
    private String parentId;
    /**
     *
     */
    private boolean root;

    /**
     *
     */
    private int sort;

    /**
     *
     */
    private int subCount;
}
