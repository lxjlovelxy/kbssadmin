package com.kbss.admin.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.kbss.admin.cms.controller.req.manage.RoleMenuItemReq;
import com.kbss.admin.cms.entity.SysMenu;
import com.kbss.admin.cms.entity.SysUserMenu;
import com.kbss.admin.cms.dao.SysUserMenuDao;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.ErrorEnums;
import com.kbss.admin.cms.service.ISysMenuService;
import com.kbss.admin.cms.service.ISysUserMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kbss.admin.cms.utils.IDUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户菜单表 服务实现类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Service
public class SysUserMenuServiceImpl extends ServiceImpl<SysUserMenuDao, SysUserMenu> implements ISysUserMenuService {
    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public List<String> getPathsBy(Long userId, RoleType role) throws Exception {
        List<SysMenu> sysMenus = sysMenuService.permissByUserId(userId,role);
        List<String> paths = new ArrayList<>();
        for (SysMenu sysMenu:sysMenus){
            if(!StringUtils.isEmpty(sysMenu.getUrl())){
                paths.add(sysMenu.getUrl());
            }
        }
        return paths;
    }

    @Override
    public Boolean save(Long userId, List<RoleMenuItemReq> permissions) throws CommonException {
        EntityWrapper filter = new EntityWrapper();
        filter.eq(SysUserMenu.USER_ID, userId);
        if (!this.delete(filter)){
            throw new CommonException(ErrorEnums.DB_DEL_FAIL);
        }
        List<SysUserMenu> userPermissions = new ArrayList<>();
        for (RoleMenuItemReq permission: permissions){
            SysUserMenu manageUserPermission = new SysUserMenu();
            manageUserPermission.setId(IDUtils.newLongID())
                    .setUserId(userId)
                    .setMenuId(permission.getMenuId())
                    .setMenuName(permission.getMenuName());
            userPermissions.add(manageUserPermission);
        }
        if (!this.insertBatch(userPermissions)){
            throw new CommonException(ErrorEnums.DB_ADD_FAILL);
        }
        return true;
    }
}
