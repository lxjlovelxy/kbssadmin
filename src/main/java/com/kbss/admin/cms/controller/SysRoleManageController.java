package com.kbss.admin.cms.controller;

import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.manage.RoleManageAddReq;
import com.kbss.admin.cms.controller.req.manage.RolePermissionReq;
import com.kbss.admin.cms.controller.req.manage.RoleQueryReq;
import com.kbss.admin.cms.controller.resp.CommonResp;
import com.kbss.admin.cms.controller.resp.manage.MenuTree;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.manage.RoleManageItemResp;
import com.kbss.admin.cms.entity.SysRole;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.CommonContext;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.CommonSession;
import com.kbss.admin.cms.service.ISysMenuService;
import com.kbss.admin.cms.service.ISysRoleMenuService;
import com.kbss.admin.cms.service.ISysRoleService;
import com.kbss.admin.cms.utils.IDUtils;
import io.swagger.annotations.Api;
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
@Api(value = "系统管理-角色管理API")
@RestController
@RequestMapping("/manage/role")
public class SysRoleManageController {

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;


    @ApiOperation(value = "1.查询角色列表", httpMethod = "POST")
    @PostMapping("/query")
    public CommonResp<PageResp<RoleManageItemResp>> query(@RequestBody @Valid PageReq<RoleQueryReq> pageReq){
        return new CommonResp<>(sysRoleService.query(pageReq));
    }

    @ApiOperation(value = "2.添加角色", httpMethod = "POST")
    @PostMapping("/add")
    public CommonResp<Boolean> add(@RequestBody @Valid RoleManageAddReq req) throws CommonException {
        CommonSession commonSession = CommonContext.getSession();
        Long orgId = null;
        RoleType roleType = commonSession.getRoleType();
        if (roleType == RoleType.SCHOOL||roleType == RoleType.SITE) {
            orgId = commonSession.getOrgId();
        }
        return new CommonResp<>(sysRoleService.add(orgId,req));
    }

    @ApiOperation(value = "3.修改角色", httpMethod = "POST")
    @PostMapping("/update")
    public CommonResp<Boolean> update(@RequestBody @Valid RoleManageAddReq req)throws CommonException{
        CommonSession commonSession = CommonContext.getSession();
        Long orgId = null;
        RoleType roleType = commonSession.getRoleType();
        if (roleType == RoleType.SCHOOL||roleType == RoleType.SITE) {
            orgId = commonSession.getOrgId();
        }
        return new CommonResp<>(sysRoleService.update(orgId,req));
    }

    @ApiOperation(value = "4.角色信息", httpMethod = "GET")
    @GetMapping("/info")
    public CommonResp<SysRole> info(@RequestParam(name = "id", required = true) @Valid Long id){
        return new CommonResp<>(sysRoleService.selectById(id));
    }

    @ApiOperation(value = "5.删除角色", httpMethod = "GET")
    @GetMapping("/del")
    public CommonResp<Boolean> del(@RequestParam(name = "id", required = true) @Valid Long id){
        return new CommonResp<>(sysRoleService.deleteById(id));
    }

    @ApiOperation(value = "6.获取角色菜单树", httpMethod = "GET")
    @GetMapping("/permission/get")
    public CommonResp<List<MenuTree>> menuTreeByRole(@RequestParam(name = "roleId", required = true) Long roleId) throws Exception {
        return new CommonResp(sysRoleMenuService.menuTreeByRole(roleId));
    }

    @ApiOperation(value = "8.保存角色权限", httpMethod = "POST")
    @PostMapping("/permission/save")
    public CommonResp<Boolean> permission(@RequestBody @Valid RolePermissionReq req) throws Exception {
        return new CommonResp<>(sysRoleMenuService.save(req.getRoleId(),req.getPermissions()));
    }

    @ApiOperation(value = "9.角色拥有的全部菜单树", httpMethod = "GET")
    @GetMapping(value = "/menu/all")
    public CommonResp<List<MenuTree>> menuTreeAll()throws CommonException{
        return new CommonResp<>(sysRoleService.menuTreeAll());
    }
}
