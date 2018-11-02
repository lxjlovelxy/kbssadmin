package com.kbss.admin.cms.controller.req.manage;

import com.kbss.admin.cms.enums.RoleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserManageItemReq {
    /**
     * 角色类型,必填
     */
    @ApiModelProperty(value = "角色类型", required = true)
    @NotBlank(message = "角色类型不为空")
    private RoleType roleType;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;
}
