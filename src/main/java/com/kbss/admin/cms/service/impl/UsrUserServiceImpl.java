package com.kbss.admin.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kbss.admin.cms.constant.SysConstant;
import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.login.LoginReq;
import com.kbss.admin.cms.controller.req.manage.UserManageAddReq;
import com.kbss.admin.cms.controller.req.manage.UserManageItemReq;
import com.kbss.admin.cms.controller.resp.CommonResp;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.login.LoginUserInfoResp;
import com.kbss.admin.cms.controller.resp.manage.UserManageItemResp;
import com.kbss.admin.cms.entity.*;
import com.kbss.admin.cms.dao.UsrUserDao;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.enums.YesOrNo;
import com.kbss.admin.cms.filter.ISessionService;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.CommonSession;
import com.kbss.admin.cms.filter.entity.ErrorEnums;
import com.kbss.admin.cms.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kbss.admin.cms.utils.CookieUtils;
import com.kbss.admin.cms.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Service
public class UsrUserServiceImpl extends ServiceImpl<UsrUserDao, UsrUser> implements IUsrUserService {
    @Autowired
    private ISessionService sessionService;
    @Autowired
    private IUsrSysService usrSysService;
    @Autowired
    private IUsrProjectService usrProjectService;
    @Autowired
    private IUsrSiteService usrSiteService;
    @Autowired
    private IUsrOrgService usrOrgService;
    @Autowired
    private ISysRoleService sysRoleService;
    /**
     * 用户登录
     *
     * @param loginReq
     * @param httpServletResponse
     * @return
     */
    @Override
    public String queryByLogin(LoginReq loginReq, HttpServletResponse httpServletResponse)throws Exception {
        EntityWrapper filter = new EntityWrapper();
        //规定：账号第一位一定是字母
        //手机号登录
        if("1".equals(loginReq.getNo().substring(0,1))){
            filter.eq(UsrUser.PHONE,loginReq.getNo()).eq(UsrUser.PASS_WORD,loginReq.getPwd());
        }
        //账号登录
        else{
            filter.eq(UsrUser.ACC,loginReq.getNo()).eq(UsrUser.PASS_WORD,loginReq.getPwd());
        }

        UsrUser user = this.selectOne(filter);
        if (user == null){
            throw new CommonException(ErrorEnums.LOGIN_FAIL);
        }

        //判断用户状态
        LoginUserInfoResp userInfo = getUserInfo(user.getId(),loginReq.getRoleType());

        CommonSession commonSession = CommonSession.builder()
                .token(IDUtils.newGUID())
                .userId(user.getId())
                .roleType(loginReq.getRoleType())
                .roleId(userInfo.getRoleId())
                .userName(userInfo.getName())
                .orgId(userInfo.getOrgId())
                .build();
        sessionService.set(commonSession);
        CookieUtils.addCookie(httpServletResponse, commonSession.getToken());

        return commonSession.getToken();
    }
    /**
     * 用户登录退出
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @Override
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String token = CookieUtils.getToken(httpServletRequest);
        if (!StringUtils.isEmpty(token)){
            sessionService.remove(token);
        }
        CookieUtils.clearCookie(httpServletResponse);
        return "ok";
    }

    private LoginUserInfoResp getUserInfo(Long userId, RoleType roleType)throws CommonException{
        String userName;
        Long roleId = null;
        Long orgId = null;
        EntityWrapper filter;
        if(roleType == RoleType.ADMIN){
            filter = new EntityWrapper();
            filter.eq(UsrSys.USER_ID,userId);
            UsrSys queryUser = usrSysService.selectOne(filter);
            if(queryUser == null){
                throw new CommonException(ErrorEnums.NO_USER);
            }

            if(queryUser.getState()!=YesOrNo.YES){
                throw new CommonException(ErrorEnums.USER_DISABLE);
            }

            userName = queryUser.getNickName();
            roleId = queryUser.getRoleId();
        }else if(roleType == RoleType.SCHOOL){
            filter = new EntityWrapper();
            filter.eq(UsrProject.USER_ID,userId);
            UsrProject queryUser = usrProjectService.selectOne(filter);
            if(queryUser == null){
                throw new CommonException(ErrorEnums.NO_USER);
            }

            if(queryUser.getState()!=YesOrNo.YES){
                throw new CommonException(ErrorEnums.USER_DISABLE);
            }

            userName = queryUser.getNickName();
            roleId = queryUser.getRoleId();
            orgId = queryUser.getProjectId();
        }else if(roleType == RoleType.SITE){
            filter = new EntityWrapper();
            filter.eq(UsrSite.USER_ID,userId);
            UsrSite queryUser = usrSiteService.selectOne(filter);
            if(queryUser == null){
                throw new CommonException(ErrorEnums.NO_USER);
            }

            if(queryUser.getState()!=YesOrNo.YES){
                throw new CommonException(ErrorEnums.USER_DISABLE);
            }

            userName = queryUser.getNickName();
            roleId = queryUser.getRoleId();
            orgId = queryUser.getSiteId();
        }else if(roleType == RoleType.STUDENT){
            userName = "未实现";
        }else if(roleType == RoleType.TEACHER){
            userName = "未实现";
        }else if(roleType == RoleType.ORG){
            filter = new EntityWrapper();
            filter.eq(UsrOrg.USER_ID,userId);
            UsrOrg queryUser = usrOrgService.selectOne(filter);
            if(queryUser == null){
                throw new CommonException(ErrorEnums.NO_USER);
            }

            if(queryUser.getState()!=YesOrNo.YES){
                throw new CommonException(ErrorEnums.USER_DISABLE);
            }

            userName = queryUser.getNickName();
            roleId = queryUser.getRoleId();
            orgId = queryUser.getOrgId();
        }else {
            throw new CommonException(ErrorEnums.SYS_ERROR,"角色类型错误");
        }

    return LoginUserInfoResp.builder()
            .name(userName)
            .userId(userId)
            .roleType(roleType)
            .roleId(roleId)
            .orgId(orgId)
            .notifyCount(0).build();
    }

    /**
     * 登录用户信息
     *
     * @param userId
     * @param roleType
     * @return
     * @throws Exception
     */
    @Override
    public LoginUserInfoResp loginUserInfo(Long userId, RoleType roleType) throws CommonException {
        return this.getUserInfo(userId,roleType);
    }

    /**
     * 角色列表
     *
     * @param pageReq
     * @return
     * @throws CommonException
     */
    @Override
    public PageResp<UserManageItemResp> list(PageReq<UserManageItemReq> pageReq) throws CommonException {
        RoleType roleType = pageReq.getQuery().getRoleType();
        String name = pageReq.getQuery().getName();
        String phone = pageReq.getQuery().getPhone();
        EntityWrapper filter = new EntityWrapper();

        if(roleType ==RoleType.ADMIN){
            if (!StringUtils.isEmpty(name)){
                filter.like(UsrSys.NICK_NAME, name);
            }
            if (!StringUtils.isEmpty(phone)){
                filter.like(UsrSys.PHONE, phone);
            }

            Page<UsrSys> page = new Page(pageReq.getIndex(),pageReq.getSize());
            page = usrSysService.selectPage(page,filter);
            List rows = new ArrayList<UserManageItemResp>();
            for (UsrSys manageUser:page.getRecords()){
                UserManageItemResp item = UserManageItemResp.builder()
                        .id(manageUser.getId())
                        .acc(manageUser.getAcc())
                        .name(manageUser.getNickName())
                        .phone(manageUser.getPhone())
                        .roleId(manageUser.getRoleId())
                        .roleType(roleType)
                        .state(manageUser.getState())
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
        }else if(roleType == RoleType.SCHOOL){
            if (!StringUtils.isEmpty(name)){
                filter.like(UsrSys.NICK_NAME, name);
            }
            if (!StringUtils.isEmpty(phone)){
                filter.like(UsrSys.PHONE, phone);
            }

            Page<UsrProject> page = new Page(pageReq.getIndex(),pageReq.getSize());
            page = usrProjectService.selectPage(page,filter);
            List rows = new ArrayList<UserManageItemResp>();
            for (UsrProject manageUser:page.getRecords()){
                UserManageItemResp item = UserManageItemResp.builder()
                        .id(manageUser.getId())
                        .acc(manageUser.getAcc())
                        .name(manageUser.getNickName())
                        .phone(manageUser.getPhone())
                        .roleId(manageUser.getRoleId())
                        .roleType(roleType)
                        .state(manageUser.getState())
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


        }else if(roleType == RoleType.SITE){
            if (!StringUtils.isEmpty(name)){
                filter.like(UsrSys.NICK_NAME, name);
            }
            if (!StringUtils.isEmpty(phone)){
                filter.like(UsrSys.PHONE, phone);
            }

            Page<UsrSite> page = new Page(pageReq.getIndex(),pageReq.getSize());
            page = usrSiteService.selectPage(page,filter);
            List rows = new ArrayList<UserManageItemResp>();
            for (UsrSite manageUser:page.getRecords()){
                UserManageItemResp item = UserManageItemResp.builder()
                        .id(manageUser.getId())
                        .acc(manageUser.getAcc())
                        .name(manageUser.getNickName())
                        .phone(manageUser.getPhone())
                        .roleId(manageUser.getRoleId())
                        .roleType(roleType)
                        .state(manageUser.getState())
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
        }else if(roleType == RoleType.STUDENT){
            throw new CommonException(ErrorEnums.SYS_ERROR,"接口未实现");
        }else if(roleType == RoleType.TEACHER){
            throw new CommonException(ErrorEnums.SYS_ERROR,"接口未实现");
        }else if(roleType == RoleType.ORG){
            if (!StringUtils.isEmpty(name)){
                filter.like(UsrSys.NICK_NAME, name);
            }
            if (!StringUtils.isEmpty(phone)){
                filter.like(UsrSys.PHONE, phone);
            }

            Page<UsrOrg> page = new Page(pageReq.getIndex(),pageReq.getSize());
            page = usrOrgService.selectPage(page,filter);
            List rows = new ArrayList<UserManageItemResp>();
            for (UsrOrg manageUser:page.getRecords()){
                UserManageItemResp item = UserManageItemResp.builder()
                        .id(manageUser.getId())
                        .acc(manageUser.getAcc())
                        .name(manageUser.getNickName())
                        .phone(manageUser.getPhone())
                        .roleId(manageUser.getRoleId())
                        .roleType(roleType)
                        .state(manageUser.getState())
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
        }else{
            throw new CommonException(ErrorEnums.CHECK_FAIL,"roleType","请选择角色类型");
        }
    }

    /**
     * 添加用户
     *
     * @param userManageAddReq
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public UserManageItemResp add(UserManageAddReq userManageAddReq) throws CommonException{
        //TODO：判断用户是否存在，如果存在判断角色类型是否一样

        //添加用户表
        UsrUser usrUser = new UsrUser();
        usrUser.setId(IDUtils.newLongID())
                .setAcc(userManageAddReq.getAcc())
                .setPhone(userManageAddReq.getPhone())
                .setPassWord("123456");
        this.insert(usrUser);

        //角色ID
        Long roleID = IDUtils.newLongID();
        String roleName = "角色名称";
        //添加子用户表
        RoleType roleType = userManageAddReq.getRoleType();
        if(roleType == RoleType.ADMIN){
            UsrSys user = new UsrSys();
            user.setId(IDUtils.newLongID())
                    .setUserId(usrUser.getId())
                    .setRoleId(roleID)
                    .setAcc(usrUser.getAcc())
                    .setPhone(usrUser.getPhone())
                    .setNickName(userManageAddReq.getName())
                    .setState(YesOrNo.YES);
            usrSysService.insert(user);
            roleName = userManageAddReq.getName()+"管理员";
        }else if(roleType == RoleType.SCHOOL){
            UsrProject user = new UsrProject();
            user.setId(IDUtils.newLongID())
                    .setUserId(usrUser.getId())
                    .setRoleId(roleID)
                    .setAcc(usrUser.getAcc())
                    .setPhone(usrUser.getPhone())
                    .setNickName(userManageAddReq.getName())
                    .setState(YesOrNo.YES);
            usrProjectService.insert(user);
            roleName = userManageAddReq.getName()+"管理员";
        }else if(roleType == RoleType.SITE){
            UsrSite user = new UsrSite();
            user.setId(IDUtils.newLongID())
                    .setUserId(usrUser.getId())
                    .setRoleId(roleID)
                    .setAcc(usrUser.getAcc())
                    .setPhone(usrUser.getPhone())
                    .setNickName(userManageAddReq.getName())
                    .setState(YesOrNo.YES);
            usrSiteService.insert(user);
            roleName = userManageAddReq.getName()+"管理员";
        }else if(roleType == RoleType.STUDENT){
            throw new CommonException(ErrorEnums.SYS_ERROR,"接口未实现");
        }else if(roleType == RoleType.TEACHER){
            throw new CommonException(ErrorEnums.SYS_ERROR,"接口未实现");
        }else if(roleType == RoleType.ORG){
            UsrOrg user = new UsrOrg();
            user.setId(IDUtils.newLongID())
                    .setUserId(usrUser.getId())
                    .setOrgId(userManageAddReq.getOrgId())
                    .setRoleId(roleID)
                    .setAcc(usrUser.getAcc())
                    .setPhone(usrUser.getPhone())
                    .setNickName(userManageAddReq.getName())
                    .setState(YesOrNo.YES);
            usrOrgService.insert(user);
            roleName = userManageAddReq.getName()+"管理员";
        }else {
            throw new CommonException(ErrorEnums.CHECK_FAIL,"roleType","请选择角色类型");
        }

        //添加系统角色表
        SysRole sysRole = new SysRole();
        sysRole.setId(roleID)
                .setRoleType(roleType)
                .setCode(IDUtils.newGUID())
                .setName(roleName);
        sysRoleService.insert(sysRole);

        UserManageItemResp resp = UserManageItemResp.builder()
                .id(usrUser.getId())
                .acc(usrUser.getAcc())
                .name(userManageAddReq.getName())
                .phone(usrUser.getPhone())
                .roleType(roleType)
                .roleId(roleID)
                .state(YesOrNo.YES).build();


        return resp;
    }

    /**
     * 修改用户
     *
     * @param userManageAddReq
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Boolean update(UserManageAddReq userManageAddReq) throws CommonException {
        UsrUser usrUser = new UsrUser();
        usrUser.setId(userManageAddReq.getId())
                .setAcc(userManageAddReq.getAcc())
                .setPhone(userManageAddReq.getPhone());
        this.updateById(usrUser);

        //更新子用户表
        RoleType roleType = userManageAddReq.getRoleType();
        if(roleType == RoleType.ADMIN){
            UsrSys user = new UsrSys();
            user.setUserId(usrUser.getId());

            UsrSys to = new UsrSys();
            to.setAcc(userManageAddReq.getAcc())
                    .setPhone(userManageAddReq.getPhone())
                    .setNickName(userManageAddReq.getName())
                    .setState(userManageAddReq.getState());

            usrSysService.update(user,new EntityWrapper<>(to));
        }else if(roleType == RoleType.SCHOOL){
            UsrProject user = new UsrProject();
            user.setUserId(usrUser.getId());

            UsrProject to = new UsrProject();
            to.setAcc(userManageAddReq.getAcc())
                    .setPhone(userManageAddReq.getPhone())
                    .setNickName(userManageAddReq.getName())
                    .setState(userManageAddReq.getState());
            usrProjectService.update(user,new EntityWrapper<>(to));
        }else if(roleType == RoleType.SITE){
            UsrSite user = new UsrSite();
            user.setUserId(usrUser.getId());

            UsrSite to = new UsrSite();
            to.setAcc(userManageAddReq.getAcc())
                    .setPhone(userManageAddReq.getPhone())
                    .setNickName(userManageAddReq.getName())
                    .setState(userManageAddReq.getState());
            usrSiteService.update(user,new EntityWrapper<>(to));
        }else if(roleType == RoleType.STUDENT){
            throw new CommonException(ErrorEnums.SYS_ERROR,"接口未实现");
        }else if(roleType == RoleType.TEACHER){
            throw new CommonException(ErrorEnums.SYS_ERROR,"接口未实现");
        }else if(roleType == RoleType.ORG){
            UsrOrg user = new UsrOrg();
            user.setUserId(usrUser.getId());

            UsrOrg to = new UsrOrg();
            to.setAcc(userManageAddReq.getAcc())
                    .setPhone(userManageAddReq.getPhone())
                    .setNickName(userManageAddReq.getName())
                    .setState(userManageAddReq.getState());
            usrOrgService.update(user,new EntityWrapper<>(to));
        }else {
            throw new CommonException(ErrorEnums.CHECK_FAIL,"roleType","请选择角色类型");
        }


        return true;
    }

    /**
     * 用户信息
     *
     * @param id
     * @param roleType
     * @return
     */
    @Override
    public UserManageItemResp info(Long id, RoleType roleType)throws CommonException {
        if(roleType == RoleType.ADMIN){
            UsrSys user = usrSysService.selectById(id);
            return UserManageItemResp.builder()
                    .id(id)
                    .acc(user.getAcc())
                    .roleType(roleType)
                    .name(user.getNickName())
                    .phone(user.getPhone())
                    .roleId(user.getRoleId())
                    .state(user.getState()).build();
        }else if(roleType == RoleType.SCHOOL){
            UsrProject user = usrProjectService.selectById(id);
            return UserManageItemResp.builder()
                    .id(id)
                    .acc(user.getAcc())
                    .roleType(roleType)
                    .name(user.getNickName())
                    .phone(user.getPhone())
                    .roleId(user.getRoleId())
                    .state(user.getState()).build();
        }else if(roleType == RoleType.SITE){
            UsrSite user = usrSiteService.selectById(id);
            return UserManageItemResp.builder()
                    .id(id)
                    .acc(user.getAcc())
                    .roleType(roleType)
                    .name(user.getNickName())
                    .phone(user.getPhone())
                    .roleId(user.getRoleId())
                    .state(user.getState()).build();
        }else if(roleType == RoleType.STUDENT){
            throw new CommonException(ErrorEnums.SYS_ERROR,"接口未实现");
        }else if(roleType == RoleType.TEACHER){
            throw new CommonException(ErrorEnums.SYS_ERROR,"接口未实现");
        }else if(roleType == RoleType.ORG){
            UsrOrg user = usrOrgService.selectById(id);
            return UserManageItemResp.builder()
                    .id(id)
                    .acc(user.getAcc())
                    .roleType(roleType)
                    .name(user.getNickName())
                    .phone(user.getPhone())
                    .roleId(user.getRoleId())
                    .state(user.getState()).build();
        }else {
            throw new CommonException(ErrorEnums.CHECK_FAIL,"roleType","请选择角色类型");
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @param roleType
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Boolean del(Long id, RoleType roleType) throws CommonException {
        //删子用户表
        //判断其他子用户表是否还有这个用户，如果没有则删总表
        Boolean adminEx = usrSysService.selectById(id)!=null;
        Boolean schoolEx = usrProjectService.selectById(id)!=null;
        Boolean siteEx = usrProjectService.selectById(id)!=null;
        Boolean studentEx = false;
        Boolean teacherEx = false;
        Boolean orgEx = usrOrgService.selectById(id)!=null;
        if(roleType == RoleType.ADMIN){
            usrSysService.deleteById(id);
            adminEx = false;
        }else if(roleType == RoleType.SCHOOL){
            usrProjectService.deleteById(id);
            schoolEx = false;
        }else if(roleType == RoleType.SITE){
            usrSiteService.deleteById(id);
            siteEx = false;
        }else if(roleType == RoleType.STUDENT){
            throw new CommonException(ErrorEnums.SYS_ERROR,"接口未实现");
        }else if(roleType == RoleType.TEACHER){
            throw new CommonException(ErrorEnums.SYS_ERROR,"接口未实现");
        }else if(roleType == RoleType.ORG){
            usrOrgService.deleteById(id);
            orgEx = false;
        }else {
            throw new CommonException(ErrorEnums.CHECK_FAIL,"roleType","请选择角色类型");
        }


        if(adminEx||schoolEx||siteEx||studentEx||teacherEx||orgEx){
            return true;
        }

        //删总表
        return this.deleteById(id);
    }
}
