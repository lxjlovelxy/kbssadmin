package com.kbss.admin.cms.controller.resp.manage;

import com.baomidou.mybatisplus.annotations.TableField;
import com.kbss.admin.cms.enums.YesOrNo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuTypeResp {
    private Long id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String memo;
    /**
     * 状态 0：停用 1：启用
     */
    private YesOrNo state;
}
