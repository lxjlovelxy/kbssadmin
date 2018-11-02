package com.kbss.admin.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.kbss.admin.cms.controller.req.manage.MenuMangeAddReq;
import com.kbss.admin.cms.controller.resp.manage.MenuTree;
import com.kbss.admin.cms.entity.SysMenu;
import com.kbss.admin.cms.dao.SysMenuDao;
import com.kbss.admin.cms.entity.SysRoleMenu;
import com.kbss.admin.cms.entity.SysUserMenu;
import com.kbss.admin.cms.entity.SysVerMenu;
import com.kbss.admin.cms.enums.Exchange;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.CommonContext;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.CommonSession;
import com.kbss.admin.cms.filter.entity.ErrorEnums;
import com.kbss.admin.cms.service.ISysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kbss.admin.cms.service.ISysRoleMenuService;
import com.kbss.admin.cms.service.ISysUserMenuService;
import com.kbss.admin.cms.service.ISysVerMenuService;
import com.kbss.admin.cms.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements ISysMenuService {
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;
    @Autowired
    private ISysUserMenuService sysUserMenuService;
    @Autowired
    private ISysVerMenuService sysVerMenuService;

    /**
     * 查询菜单
     *
     * @param menuTypeId
     * @return
     * @throws CommonException
     */
    @Override
    public List<MenuTree> query(Long menuTypeId) {
        EntityWrapper filter = new EntityWrapper();
        if(menuTypeId!=null){
            filter.in(SysMenu.STYLE_ID,new Long[]{menuTypeId,0L});
        }
        filter.ge(SysMenu.GRADE,0);
        filter.orderBy(SysMenu.GRADE,true).orderBy(SysMenu.POS,true);
        List<SysMenu> sysMenus = this.selectList(filter);
        Map<Long,MenuTree> menuTreeMap = new HashMap<>();
        List<MenuTree> homeMenus = new ArrayList<>();
        for (SysMenu sysMenu:sysMenus){
            MenuTree homeMenu = new MenuTree();
            homeMenu.setId(sysMenu.getId());
            homeMenu.setParentId(sysMenu.getParentId());
            homeMenu.setIco(sysMenu.getIco());
            homeMenu.setGrade(sysMenu.getGrade());
            homeMenu.setPos(sysMenu.getPos());
            homeMenu.setCode(sysMenu.getCode());
            homeMenu.setName(sysMenu.getName());
            homeMenu.setUrl(sysMenu.getUrl());
            if (sysMenu.getGrade() == 0){
                homeMenu.setChildren(new ArrayList<>());
                menuTreeMap.put(homeMenu.getId(), homeMenu);
                homeMenus.add(homeMenu);
            }else{
                if (sysMenu.getGrade() == 1 || sysMenu.getGrade() == 2){
                    homeMenu.setChildren(new ArrayList<>());
                    menuTreeMap.put(homeMenu.getId(), homeMenu);
                }
                menuTreeMap.get(homeMenu.getParentId()).getChildren().add(homeMenu);
            }
        }
        return homeMenus;
    }
    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Boolean del(Long menuId) {
        //删除菜单表
        this.deleteById(menuId);

        //删除角色菜单
        EntityWrapper filter = new EntityWrapper();
        filter.eq(SysRoleMenu.MENU_ID,menuId);
        sysRoleMenuService.delete(filter);

        //删除用户菜单
        filter = new EntityWrapper();
        filter.eq(SysUserMenu.MENU_ID,menuId);
        sysUserMenuService.delete(filter);

        //删除版本菜单
        filter = new EntityWrapper();
        filter.eq(SysVerMenu.MENU_ID,menuId);
        sysVerMenuService.delete(filter);

        return true;
    }

    @Override
    public List<SysMenu> permissByUserId(Long userId,RoleType roleType) {
        List<SysMenu> sysMenus = baseMapper.menuByUserId(userId,roleType);
        return sysMenus;
    }
    /**
     * 指定用户ID拥有的菜单列表
     * @param userId
     * @param roleType
     * @return
     * @throws Exception
     */
    @Override
    public List<MenuTree> menuTreeByUserId(Long userId, RoleType roleType) {
        List<SysMenu> sysMenus = baseMapper.menuByUserId(userId,roleType);

        Map<Long,MenuTree> menuTreeMap = new HashMap<>();
        List<MenuTree> homeMenus = new ArrayList<>();
        for (SysMenu sysMenu:sysMenus){
            MenuTree homeMenu = new MenuTree();
            homeMenu.setId(sysMenu.getId());
            homeMenu.setParentId(sysMenu.getParentId());
            homeMenu.setIco(sysMenu.getIco());
            homeMenu.setCode(sysMenu.getCode());
            homeMenu.setName(sysMenu.getName());
            homeMenu.setUrl(sysMenu.getUrl());
            if (sysMenu.getGrade() == 1){
                homeMenu.setChildren(new ArrayList<>());
                menuTreeMap.put(homeMenu.getId(), homeMenu);
                homeMenus.add(homeMenu);
            }else{
                if (sysMenu.getGrade() == 2){
                    homeMenu.setChildren(new ArrayList<>());
                    menuTreeMap.put(homeMenu.getId(), homeMenu);
                }
                menuTreeMap.get(homeMenu.getParentId()).getChildren().add(homeMenu);
            }
        }

        return homeMenus;
    }

    /**
     * 所有菜单
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<MenuTree> menuTreeRoot(){
        EntityWrapper filter = new EntityWrapper();
        filter.ge(SysMenu.GRADE,0);
        filter.orderBy(SysMenu.GRADE,true).orderBy(SysMenu.POS,true);
        List<SysMenu> sysMenus = this.selectList(filter);
        Map<Long,MenuTree> menuTreeMap = new HashMap<>();
        List<MenuTree> homeMenus = new ArrayList<>();
        for (SysMenu sysMenu:sysMenus){
            MenuTree homeMenu = new MenuTree();
            homeMenu.setId(sysMenu.getId());
            homeMenu.setParentId(sysMenu.getParentId());
            homeMenu.setIco(sysMenu.getIco());
            homeMenu.setGrade(sysMenu.getGrade());
            homeMenu.setPos(sysMenu.getPos());
            homeMenu.setCode(sysMenu.getCode());
            homeMenu.setName(sysMenu.getName());
            homeMenu.setUrl(sysMenu.getUrl());
            if (sysMenu.getGrade() == 0){
                homeMenu.setChildren(new ArrayList<>());
                menuTreeMap.put(homeMenu.getId(), homeMenu);
                homeMenus.add(homeMenu);
            }else{
                if (sysMenu.getGrade() == 1 || sysMenu.getGrade() == 2){
                    homeMenu.setChildren(new ArrayList<>());
                    menuTreeMap.put(homeMenu.getId(), homeMenu);
                }
                menuTreeMap.get(homeMenu.getParentId()).getChildren().add(homeMenu);
            }
        }
        return homeMenus;
    }

    /**
     * 全部菜单
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<MenuTree> menuTreeAll() {
        EntityWrapper filter = new EntityWrapper();
        filter.gt(SysMenu.GRADE,0);
        filter.orderBy(SysMenu.GRADE,true).orderBy(SysMenu.POS,true);
        List<SysMenu> sysMenus = this.selectList(filter);
        Map<Long,MenuTree> menuTreeMap = new HashMap<>();
        List<MenuTree> homeMenus = new ArrayList<>();
        for (SysMenu sysMenu:sysMenus){
            MenuTree homeMenu = new MenuTree();
            homeMenu.setId(sysMenu.getId());
            homeMenu.setParentId(sysMenu.getParentId());
            homeMenu.setIco(sysMenu.getIco());
            homeMenu.setCode(sysMenu.getCode());
            homeMenu.setName(sysMenu.getName());
            homeMenu.setUrl(sysMenu.getUrl());
            if (sysMenu.getGrade() == 1){
                homeMenu.setChildren(new ArrayList<>());
                menuTreeMap.put(homeMenu.getId(), homeMenu);
                homeMenus.add(homeMenu);
            }else{
                if (sysMenu.getGrade() == 2){
                    homeMenu.setChildren(new ArrayList<>());
                    menuTreeMap.put(homeMenu.getId(), homeMenu);
                }
                menuTreeMap.get(homeMenu.getParentId()).getChildren().add(homeMenu);
            }
        }
        return homeMenus;
    }

    /**
     * 添加菜单
     *
     * @param menuMangeAddReq
     * @return
     * @throws CommonException
     */
    @Override
    public Boolean add(MenuMangeAddReq menuMangeAddReq) throws CommonException {
        SysMenu pMenu = this.selectById(menuMangeAddReq.getParentId());
        if (pMenu == null){
            throw new CommonException(ErrorEnums.PID_NO_EXIST);
        }
        int maxPos = baseMapper.maxPosByPid(pMenu.getId());

        CommonSession commonSession = CommonContext.getSession();
        Long loginUserId = commonSession.getUserId();

        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(IDUtils.newLongID())
                .setGrade(pMenu.getGrade()+1)
                .setPos(maxPos+1)
                .setCode(menuMangeAddReq.getCode())
                .setName(menuMangeAddReq.getName())
                .setParentId(pMenu.getId())
                .setUrl(menuMangeAddReq.getUrl())
                .setIco(menuMangeAddReq.getIco())
                .setStyleId(menuMangeAddReq.getMenuTypeId())
                .setGmtCreater(loginUserId)
                .setGmtModifier(loginUserId);
        return this.insert(sysMenu);
    }

    /**
     * 修改菜单
     *
     * @param menuMangeAddReq
     * @return
     * @throws CommonException
     */
    @Override
    public Boolean update(MenuMangeAddReq menuMangeAddReq)throws CommonException {
        CommonSession commonSession = CommonContext.getSession();
        Long loginUserId = commonSession.getUserId();

        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(menuMangeAddReq.getId())
                .setCode(menuMangeAddReq.getCode())
                .setName(menuMangeAddReq.getName())
                .setParentId(menuMangeAddReq.getParentId())
                .setUrl(menuMangeAddReq.getUrl())
                .setIco(menuMangeAddReq.getIco())
                .setStyleId(menuMangeAddReq.getMenuTypeId())
                .setGmtModifier(loginUserId);
        return this.updateById(sysMenu);
    }

    /**
     * 菜单下移
     *
     * @param id
     * @return
     * @throws CommonException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Boolean down(Long id) throws CommonException {
        exchange(Exchange.DOWN,id);
        return true;
    }

    /**
     * 菜单上移
     *
     * @param id
     * @return
     * @throws CommonException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Boolean up(Long id) throws CommonException {
        exchange(Exchange.UP,id);
        return true;
    }

    private void exchange(Exchange exchange, Long id) throws CommonException {
        SysMenu sysMenu = this.selectById(id);
        if(sysMenu == null){
            throw new CommonException(ErrorEnums.MENU_NO_EXIST);
        }
        EntityWrapper filter = new EntityWrapper();
        if (exchange == Exchange.UP){
            filter.lt(SysMenu.POS, sysMenu.getPos()).eq(SysMenu.PARENT_ID, sysMenu.getParentId());
            filter.orderBy(SysMenu.POS,false);
        } else{
            filter.gt(SysMenu.POS, sysMenu.getPos()).eq(SysMenu.PARENT_ID, sysMenu.getParentId());
            filter.orderBy(SysMenu.POS,true);
        }
        List<SysMenu> manageMenus = this.selectList(filter);
        if (manageMenus.size() == 0){
            throw new CommonException(ErrorEnums.MENU_NO_MOVE);
        }
        //交换位置
        int tmpPos = sysMenu.getPos();
        SysMenu tmpMenu = manageMenus.get(0);
        sysMenu.setPos(tmpMenu.getPos());
        tmpMenu.setPos(tmpPos);
        if(!this.updateById(sysMenu)){
            throw new CommonException(ErrorEnums.DB_UPDATE_FAIL);
        }
        if(!this.updateById(tmpMenu)){
            throw new CommonException(ErrorEnums.DB_UPDATE_FAIL);
        }
    }
}
