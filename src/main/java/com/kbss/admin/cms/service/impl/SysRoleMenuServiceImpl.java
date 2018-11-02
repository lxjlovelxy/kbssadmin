package com.kbss.admin.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.kbss.admin.cms.controller.req.manage.RoleMenuItemReq;
import com.kbss.admin.cms.controller.resp.manage.MenuTree;
import com.kbss.admin.cms.entity.SysMenu;
import com.kbss.admin.cms.entity.SysRoleMenu;
import com.kbss.admin.cms.dao.SysRoleMenuDao;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.ErrorEnums;
import com.kbss.admin.cms.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kbss.admin.cms.utils.IDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统角色菜单表 服务实现类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements ISysRoleMenuService {

    /**
     * 角色菜单树
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    @Override
    public List<MenuTree> menuTreeByRole(Long roleId) throws CommonException {
        List<SysMenu> sysRoleMenus = baseMapper.menuByRoleId(roleId);
        Map<Long,MenuTree> menuTreeMap = new HashMap<>();
        List<MenuTree> homeMenus = new ArrayList<>();
        for (SysMenu sysRoleMenu:sysRoleMenus){
            MenuTree menuTree = new MenuTree();
            menuTree.setId(sysRoleMenu.getId());
            menuTree.setParentId(sysRoleMenu.getParentId());
            menuTree.setIco(sysRoleMenu.getIco());
            menuTree.setName(sysRoleMenu.getName());
            menuTree.setCode(sysRoleMenu.getCode());
            menuTree.setUrl(sysRoleMenu.getUrl());
            if (menuTree.getGrade() == 1){
                menuTree.setChildren(new ArrayList<>());
                menuTreeMap.put(menuTree.getId(), menuTree);
                homeMenus.add(menuTree);
            }else{
                menuTreeMap.get(menuTree.getParentId()).getChildren().add(menuTree);
            }
        }
        return homeMenus;
    }

    /**
     * 保存角色权限
     *
     * @param roleId
     * @param menuItems
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Boolean save(Long roleId, List<RoleMenuItemReq> menuItems) throws CommonException {
        EntityWrapper filter = new EntityWrapper();
        filter.eq(SysRoleMenu.ROLE_ID, roleId);
        if (!this.delete(filter)){
            throw new CommonException(ErrorEnums.DB_DEL_FAIL);
        }
        List<SysRoleMenu> roleMenus = new ArrayList<>();
        for (RoleMenuItemReq item: menuItems){
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setId(IDUtils.newLongID())
                    .setMenuId(item.getMenuId())
                    .setMenuName(item.getMenuName())
                    .setRoleId(roleId);
            roleMenus.add(roleMenu);
        }
        if (!this.insertBatch(roleMenus)){
            throw new CommonException(ErrorEnums.DB_ADD_FAILL);
        }
        return true;
    }
}
