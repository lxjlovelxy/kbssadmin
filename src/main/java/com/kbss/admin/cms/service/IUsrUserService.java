package com.kbss.admin.cms.service;

import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.login.LoginReq;
import com.kbss.admin.cms.controller.req.manage.UserManageAddReq;
import com.kbss.admin.cms.controller.req.manage.UserManageItemReq;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.login.LoginUserInfoResp;
import com.kbss.admin.cms.controller.resp.manage.UserManageItemResp;
import com.kbss.admin.cms.entity.UsrUser;
import com.baomidou.mybatisplus.service.IService;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.entity.CommonException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
public interface IUsrUserService extends IService<UsrUser> {
    /**
     * 用户登录
     * @param loginReq
     * @param httpServletResponse
     * @return token
     */
    public String queryByLogin(LoginReq loginReq, HttpServletResponse httpServletResponse)throws Exception;
    /**
     * 用户登录退出
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)throws Exception;

    /**
     * 登录用户信息
     * @param userId
     * @param roleType
     * @return
     * @throws Exception
     */
    LoginUserInfoResp loginUserInfo(Long userId, RoleType roleType) throws CommonException;

    /**
     * 用户列表
     * @param pageReq
     * @return
     * @throws CommonException
     */
    PageResp<UserManageItemResp> list(PageReq<UserManageItemReq> pageReq)throws CommonException;

    /**
     * 添加用户
     * @param userManageAddReq
     * @return
     */
    UserManageItemResp add(UserManageAddReq userManageAddReq)throws CommonException;
    /**
     * 修改用户
     * @param userManageAddReq
     * @return
     */
    Boolean update(UserManageAddReq userManageAddReq)throws CommonException;
    /**
     * 用户信息
     * @param
     * @return
     */
    UserManageItemResp info(Long id,RoleType roleType)throws CommonException;
    /**
     * 删除用户
     * @param
     * @return
     */
    Boolean del(Long id,RoleType roleType)throws CommonException;
}
