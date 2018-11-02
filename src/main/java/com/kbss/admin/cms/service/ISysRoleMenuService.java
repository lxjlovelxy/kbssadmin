package com.kbss.admin.cms.service;

import com.kbss.admin.cms.controller.req.manage.RoleMenuItemReq;
import com.kbss.admin.cms.controller.resp.manage.MenuTree;
import com.kbss.admin.cms.entity.SysRoleMenu;
import com.baomidou.mybatisplus.service.IService;
import com.kbss.admin.cms.filter.entity.CommonException;

import java.util.List;

/**
 * <p>
 * 系统角色菜单表 服务类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {
    /**
     * 角色菜单树
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<MenuTree> menuTreeByRole(Long roleId) throws CommonException;

    /**
     * 保存角色权限
     * @param roleId
     * @param menuItems
     * @return
     * @throws Exception
     */
    public Boolean save(Long roleId,List<RoleMenuItemReq> menuItems) throws CommonException;
}
