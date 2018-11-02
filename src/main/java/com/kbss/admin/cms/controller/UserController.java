package com.kbss.admin.cms.controller;

import com.kbss.admin.cms.controller.req.login.LoginReq;
import com.kbss.admin.cms.controller.resp.CommonResp;
import com.kbss.admin.cms.controller.resp.manage.MenuTree;
import com.kbss.admin.cms.controller.resp.login.LoginUserInfoResp;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.CommonContext;
import com.kbss.admin.cms.filter.entity.CommonSession;
import com.kbss.admin.cms.service.ISysMenuService;
import com.kbss.admin.cms.service.IUsrUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


/**
 * <p></p>
 * <p>Created by qrf on 2018/10/24.</p>
 *
 * @author qrf
 */
@Api(value = "登录API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUsrUserService usrUserService;
    @Autowired
    private ISysMenuService sysMenuService;

    @ApiOperation(value = "1.用户登录", httpMethod = "POST",
            notes = "返回登录token")
    @PostMapping("/login")
    public CommonResp<String> login(@RequestBody @Valid LoginReq loginReq, HttpServletResponse httpServletResponse) throws Exception {
        return new CommonResp<>(usrUserService.queryByLogin(loginReq,httpServletResponse));
    }

    @ApiOperation(value = "2.用户登录退出", httpMethod = "GET",
            notes = "返回ok表示退出成功")
    @GetMapping("/logout")
    public CommonResp<String> logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return new CommonResp<>(usrUserService.logout(httpServletRequest,httpServletResponse));
    }

    @ApiOperation(value = "3.登录用户信息", httpMethod = "POST")
    @PostMapping("/loginUserInfo")
    public CommonResp<LoginUserInfoResp> loginUserInfo() {
        CommonSession commonSession = CommonContext.getSession();
        Long userId = commonSession.getUserId();
        RoleType roleType = commonSession.getRoleType();
        Long roleId = commonSession.getRoleId();
        //return new CommonResp<>(usrUserService.loginUserInfo(userId,role));
        String userName = commonSession.getUserName();
        LoginUserInfoResp resp = LoginUserInfoResp.builder()
                .name(userName)
                .userId(userId)
                .notifyCount(0)
                .roleType(roleType)
                .roleId(roleId).build();
        return new CommonResp<>(resp);
    }

    @ApiOperation(value = "4.登录用户菜单列表", httpMethod = "POST")
    @PostMapping("/menu")
    public CommonResp<List<MenuTree>> menuTree() throws Exception{
        CommonSession commonSession = CommonContext.getSession();
        Long userId = commonSession.getUserId();
        RoleType roleType = commonSession.getRoleType();
        return new CommonResp<>(sysMenuService.menuTreeByUserId(userId,roleType));
    }
}
