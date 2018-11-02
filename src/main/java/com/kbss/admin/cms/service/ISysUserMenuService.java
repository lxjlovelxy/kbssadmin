package com.kbss.admin.cms.service;

import com.kbss.admin.cms.controller.req.manage.RoleMenuItemReq;
import com.kbss.admin.cms.entity.SysUserMenu;
import com.baomidou.mybatisplus.service.IService;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.entity.CommonException;

import java.util.List;

/**
 * <p>
 * 用户菜单表 服务类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
public interface ISysUserMenuService extends IService<SysUserMenu> {
    List<String> getPathsBy(Long userId, RoleType roleType) throws Exception;

    Boolean save(Long userId,List<RoleMenuItemReq> permissions) throws CommonException;
}
