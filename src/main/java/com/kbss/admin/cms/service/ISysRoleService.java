package com.kbss.admin.cms.service;

import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.manage.RoleManageAddReq;
import com.kbss.admin.cms.controller.req.manage.RoleQueryReq;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.manage.MenuTree;
import com.kbss.admin.cms.controller.resp.manage.RoleManageItemResp;
import com.kbss.admin.cms.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.entity.CommonException;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 查询角色列表
     * @param pageReq
     * @return
     */
    public PageResp<RoleManageItemResp> query(PageReq<RoleQueryReq> pageReq);

    /**
     * 添加角色
     * @param req
     * @return
     * @throws CommonException
     */
    public Boolean add(Long orgId,RoleManageAddReq req)throws CommonException;
    /**
     * 修改角色
     * @param req
     * @return
     * @throws CommonException
     */
    public Boolean update(Long orgId,RoleManageAddReq req)throws CommonException;

    /**
     * 角色拥有的菜单
     * @return
     * @throws CommonException
     */
    public List<MenuTree> menuTreeAll()throws CommonException;
}
