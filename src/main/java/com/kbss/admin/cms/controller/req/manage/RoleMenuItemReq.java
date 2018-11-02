package com.kbss.admin.cms.controller.req.manage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuItemReq {
    private Long menuId;

    private String menuName;
}
