package com.kbss.admin.cms.controller.req.manage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDicAddReq {
    /**
     * ID
     */
    private Long id;
    /**
     * 分类
     */
    private String style;
    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    private String name;
    /**
     * 值
     */
    private String value;
    /**
     * 值2
     */
    private Integer value2;
    /**
     * 值3
     */
    private Integer value3;
    /**
     * 备注
     */
    private String memo;
    /**
     * 备注
     */
    private String memo2;
    /**
     * 备注
     */
    private String memo3;
}
