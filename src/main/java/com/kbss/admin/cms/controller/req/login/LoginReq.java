package com.kbss.admin.cms.controller.req.login;

import com.kbss.admin.cms.enums.RoleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * <p></p>
 * <p>Created by qrf on 2018/10/25.</p>
 *
 * @author qrf
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {
    @ApiModelProperty(value = "登录账号", required = true)
    @NotBlank(message = "帐号不为空")
    private String no;

    @ApiModelProperty(value = "登录密码", required = true)
    @NotBlank(message = "密码不为空")
    private String pwd;

    @ApiModelProperty(value = "角色类型", required = true)
    @NotNull(message = "角色类型不为空")
    private RoleType roleType;
}
