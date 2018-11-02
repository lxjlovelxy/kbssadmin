package com.kbss.admin.cms.controller.req.manage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p></p>
 * <p>Created by qrf on 2018/10/27.</p>
 *
 * @author qrf
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionReq {

    private Long userId;

    private List<RoleMenuItemReq> permissions;
}
