package com.kbss.admin.cms.service;

import com.kbss.admin.cms.controller.req.manage.MenuMangeAddReq;
import com.kbss.admin.cms.controller.resp.manage.MenuTree;
import com.kbss.admin.cms.entity.SysMenu;
import com.baomidou.mybatisplus.service.IService;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.entity.CommonException;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 查询菜单
     * @param menuTypeId
     * @return
     * @throws CommonException
     */
    public List<MenuTree> query(Long menuTypeId);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    public Boolean del(Long menuId);
    /**
     * 用户ID菜单权限
     * @param userId
     * @param roleType
     * @return
     * @throws Exception
     */
    List<SysMenu> permissByUserId(Long userId,RoleType roleType) throws Exception;

    /**
     * 指定用户ID拥有的菜单列表
     * @param userId
     * @param roleType
     * @return
     * @throws Exception
     */
    public List<MenuTree> menuTreeByUserId(Long userId,RoleType roleType) throws Exception;

    /**
     * 添加菜单
     * @param menuMangeAddReq
     * @return
     * @throws CommonException
     */
    public Boolean add(MenuMangeAddReq menuMangeAddReq) throws CommonException;

    /**
     * 修改菜单
     * @param menuMangeAddReq
     * @return
     * @throws CommonException
     */
    public Boolean update(MenuMangeAddReq menuMangeAddReq) throws CommonException;

    /**
     * 菜单下移
     * @param id
     * @return
     * @throws CommonException
     */
    public Boolean down(Long id) throws CommonException;

    /**
     * 菜单上移
     * @param id
     * @return
     * @throws CommonException
     */
    public Boolean up(Long id) throws CommonException;

    /**
     * 菜单
     * @return
     * @throws Exception
     */
    List<MenuTree> menuTreeRoot();

    /**
     * 全部菜单
     * @return
     * @throws Exception
     */
    public List<MenuTree> menuTreeAll();
}
