package com.kbss.admin.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.manage.RoleManageAddReq;
import com.kbss.admin.cms.controller.req.manage.RoleQueryReq;
import com.kbss.admin.cms.controller.resp.CommonResp;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.manage.MenuTree;
import com.kbss.admin.cms.controller.resp.manage.RoleManageItemResp;
import com.kbss.admin.cms.entity.SysRole;
import com.kbss.admin.cms.dao.SysRoleDao;
import com.kbss.admin.cms.entity.UsrProject;
import com.kbss.admin.cms.entity.UsrSite;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.CommonContext;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.CommonSession;
import com.kbss.admin.cms.filter.entity.ErrorEnums;
import com.kbss.admin.cms.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kbss.admin.cms.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements ISysRoleService {
    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private IUsrProjectService usrProjectService;
    @Autowired
    private IUsrSiteService usrSiteService;
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;
    /**
     * 查询角色列表
     *
     * @param pageReq
     * @return
     */
    @Override
    public PageResp<RoleManageItemResp> query(PageReq<RoleQueryReq> pageReq) {
        String code = pageReq.getQuery().getCode();
        String name = pageReq.getQuery().getName();

        EntityWrapper filter = new EntityWrapper();
        if (!StringUtils.isEmpty(code)){
            filter.like(SysRole.CODE, code);
        }
        if (!StringUtils.isEmpty(name)){
            filter.like(SysRole.NAME, name);
        }

        Page<SysRole> page = new Page(pageReq.getIndex(),pageReq.getSize());
        page = this.selectPage(page,filter);
        List rows = new ArrayList<>();
        for (SysRole sysRole:page.getRecords()){
            RoleManageItemResp item = RoleManageItemResp.builder()
                    .id(sysRole.getId())
                    .code(sysRole.getCode())
                    .name(sysRole.getName())
                    .roleType(sysRole.getRoleType())
                    .orgId(sysRole.getOrgId())
                    .build();
            rows.add(item);
        }
        PageResp pageResp = PageResp.builder()
                .index(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .rows(rows)
                .build();
        return pageResp;
    }

    /**
     * 添加角色
     *
     * @param req
     * @return
     * @throws CommonException
     */
    @Override
    public Boolean add(Long orgId,RoleManageAddReq req) throws CommonException {
        EntityWrapper filter = new EntityWrapper();
        filter.eq(SysRole.CODE,req.getCode());
        if(orgId!=null){
            filter.eq(SysRole.ORG_ID,orgId);
        }else{
            filter.isNull(SysRole.ORG_ID);
        }

        if(this.selectCount(filter)>0){
            throw new CommonException(ErrorEnums.DB_ADD_EXIST,req.getCode());
        }

        CommonSession commonSession = CommonContext.getSession();
        Long loginUserId = commonSession.getUserId();
        RoleType roleType = commonSession.getRoleType();

        SysRole sysRole = new SysRole();
        sysRole.setId(IDUtils.newLongID())
                .setCode(req.getCode())
                .setName(req.getName())
                .setOrgId(orgId)
                .setRoleType(roleType)
                .setGmtCreater(loginUserId)
                .setGmtModifier(loginUserId);
        return this.insert(sysRole);
    }

    /**
     * 修改角色
     *
     * @param orgId
     * @param req
     * @return
     * @throws CommonException
     */
    @Override
    public Boolean update(Long orgId, RoleManageAddReq req) throws CommonException {
        EntityWrapper filter = new EntityWrapper();
        filter.eq(SysRole.CODE,req.getCode());
        filter.ne(SysRole.ID,req.getId());
        if(orgId!=null){
            filter.eq(SysRole.ORG_ID,orgId);
        }else{
            filter.isNull(SysRole.ORG_ID);
        }

        if(this.selectCount(filter)>0){
            throw new CommonException(ErrorEnums.DB_UPDATE_EXIST,req.getCode());
        }

        CommonSession commonSession = CommonContext.getSession();
        Long loginUserId = commonSession.getUserId();
        RoleType roleType = commonSession.getRoleType();

        SysRole sysRole = new SysRole();
        sysRole.setId(req.getId())
                .setCode(req.getCode())
                .setName(req.getName())
                .setOrgId(orgId)
                .setRoleType(roleType)
                .setGmtModifier(loginUserId);
        return this.updateById(sysRole);
    }

    /**
     * 角色拥有的菜单
     *
     * @return
     * @throws CommonException
     */
    @Override
    public List<MenuTree> menuTreeAll() throws CommonException {
        CommonSession commonSession = CommonContext.getSession();
        RoleType roleType = commonSession.getRoleType();

        //管理员
        if(roleType == RoleType.ADMIN){
            return sysMenuService.menuTreeAll();
        }

        Long userId = commonSession.getUserId();
        Long roleId = null;
        //学校
        if (roleType == RoleType.SCHOOL) {
            //查找父ID为空的数据，即管理员ID，找到角色ID
            EntityWrapper filter = new EntityWrapper();
            filter.eq(UsrProject.USER_ID,userId);
            filter.isNull(UsrProject.PARENT_ID);
            filter.setSqlSelect(UsrProject.ROLE_ID);
            UsrProject up = usrProjectService.selectOne(filter);
            roleId = up.getRoleId();
        }

        if (roleType == RoleType.SITE) {
            //查找父ID为空的数据，即管理员ID，找到角色ID
            EntityWrapper filter = new EntityWrapper();
            filter.eq(UsrProject.USER_ID,userId);
            filter.isNull(UsrProject.PARENT_ID);
            filter.setSqlSelect(UsrProject.ROLE_ID);
            UsrSite us = usrSiteService.selectOne(filter);
            roleId = us.getRoleId();
        }

        if(roleId ==null){
            throw new CommonException(ErrorEnums.NO_PERMISSON);

        }

        return sysRoleMenuService.menuTreeByRole(roleId);
    }
}
