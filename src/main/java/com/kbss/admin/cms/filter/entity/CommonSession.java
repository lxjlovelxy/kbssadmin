package com.kbss.admin.cms.filter.entity;

import com.kbss.admin.cms.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class CommonSession {
    //toke
    private String token;
    //用户id
    private Long userId;
    //角色类型：1:系统 2:学校 3:站点 4:学员 5:教师
    private RoleType roleType;
    //所属机构ID，如果是系统管理员则为空
    private Long orgId;
    //角色ID
    private Long roleId;
    //用户名称
    private String userName;
}
