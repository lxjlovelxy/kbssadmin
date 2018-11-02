package com.kbss.admin.cms.controller.resp.manage;

import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.enums.YesOrNo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p></p>
 * <p>Created by qrf on 2018/10/26.</p>
 *
 * @author qrf
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserManageItemResp {

    /**
     * ID
     */
    private Long id;
    /**
     * 账号
     */
    private String acc;
    /**
     * 名称
     */
    private String name;
    /**
     * 手机
     */
    private String phone;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色类型
     */
    private RoleType roleType;

    /**
     * 状态
     */
    private YesOrNo state;
}
