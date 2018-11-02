package com.kbss.admin.cms.controller.resp.manage;

import com.kbss.admin.cms.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleManageItemResp {
    /**
     * 角色ID
     */
    private Long id;
    /**
     * 角色类型
     */
    private RoleType roleType;
    /**
     * 机构ID
     */
    private Long orgId;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;

}
