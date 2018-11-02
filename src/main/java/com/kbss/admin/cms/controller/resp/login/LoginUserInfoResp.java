package com.kbss.admin.cms.controller.resp.login;

import com.kbss.admin.cms.enums.RoleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserInfoResp {
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    @ApiModelProperty(value = "角色类型", required = true)
    private RoleType roleType;

    @ApiModelProperty(value = "角色Id", required = true)
    private Long roleId;

    @ApiModelProperty(value = "所属机构Id", required = true)
    private Long orgId;

    @ApiModelProperty(value = "通知数", required = true)
    private Integer notifyCount;
}
