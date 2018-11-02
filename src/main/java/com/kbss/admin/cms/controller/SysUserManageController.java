package com.kbss.admin.cms.controller;


import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.manage.UserManageAddReq;
import com.kbss.admin.cms.controller.req.manage.UserManageItemReq;
import com.kbss.admin.cms.controller.req.manage.UserPermissionReq;
import com.kbss.admin.cms.controller.resp.CommonResp;
import com.kbss.admin.cms.controller.resp.manage.MenuTree;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.manage.UserManageItemResp;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.service.ISysMenuService;
import com.kbss.admin.cms.service.ISysUserMenuService;
import com.kbss.admin.cms.service.IUsrUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p></p>
 * <p>Created by qrf on 2018/10/26.</p>
 *
 * @author qrf
 */
@RestController
@RequestMapping("/manage/user")
public class SysUserManageController {

    @Autowired
    private IUsrUserService usrUserService;
    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private ISysUserMenuService sysUserMenuService;

    @ApiOperation(value = "1.用户列表", httpMethod = "POST")
    @PostMapping("/list")
    public CommonResp<PageResp<UserManageItemResp>> list(@RequestBody @Valid PageReq<UserManageItemReq> pageReq) throws CommonException {
        return new CommonResp<>(usrUserService.list(pageReq));
    }

    @ApiOperation(value = "2.新增用户", httpMethod = "POST")
    @PostMapping("/add")
    public CommonResp<UserManageItemResp> add(@RequestBody @Valid UserManageAddReq userManageAddReq)throws CommonException{
        return new CommonResp<>(usrUserService.add(userManageAddReq));
    }

    @ApiOperation(value = "3.更新用户", httpMethod = "POST")
    @PostMapping("/update")
    public CommonResp<Boolean> update(@RequestBody @Valid UserManageAddReq userManageAddReq)throws CommonException{
        return new CommonResp<>(usrUserService.update(userManageAddReq));
    }

    @ApiOperation(value = "4.用户信息", httpMethod = "GET")
    @GetMapping("/info")
    public CommonResp<UserManageItemResp> info(@RequestParam(name = "userId", required = true) @Valid Long userId,
                                               @RequestParam(name = "roleType", required = true) RoleType roleType)throws CommonException{
        return new CommonResp<>(usrUserService.info(userId,roleType));
    }

    @ApiOperation(value = "5.删除用户", httpMethod = "GET")
    @GetMapping("/del")
    public CommonResp<Boolean> del(@RequestParam(name = "userId", required = true) @Valid Long userId,
                                   @RequestParam(name = "roleType", required = true) RoleType roleType)throws CommonException{
        return new CommonResp<>(usrUserService.del(userId,roleType));
    }

    @ApiOperation(value = "6.获取用户菜单权限", httpMethod = "GET")
    @GetMapping("/permission/get")
    public CommonResp<List<MenuTree>> menuTree(@RequestParam(name = "userId", required = true) @Valid Long userId,
                                               @RequestParam(name = "roleType", required = true) RoleType roleType) throws Exception {
        return new CommonResp(sysMenuService.menuTreeByUserId(userId,roleType));
    }

    @ApiOperation(value = "7.保存用户菜单权限", httpMethod = "POST")
    @PostMapping("/permission/save")
    public CommonResp<Boolean> permission(@RequestBody @Valid UserPermissionReq userPermissionReq) throws Exception {
        return new CommonResp<>(sysUserMenuService.save(userPermissionReq.getUserId(),userPermissionReq.getPermissions()));
    }

    @ApiOperation(value = "8.全部菜单", httpMethod = "GET")
    @GetMapping(value = "/menu/all")
    public CommonResp<List<MenuTree>> menuTreeAll(){
        return new CommonResp<>(sysMenuService.menuTreeAll());
    }
}
