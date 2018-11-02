package com.kbss.admin.cms.controller;

import com.kbss.admin.cms.controller.req.manage.MenuMangeAddReq;
import com.kbss.admin.cms.controller.req.manage.MenuQueryReq;
import com.kbss.admin.cms.controller.resp.CommonResp;
import com.kbss.admin.cms.controller.resp.manage.MenuTree;
import com.kbss.admin.cms.controller.resp.manage.DataDicTree;
import com.kbss.admin.cms.controller.resp.manage.MenuTypeTree;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.CommonContext;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.CommonSession;
import com.kbss.admin.cms.service.ISysMenuService;
import com.kbss.admin.cms.service.ISysMenuTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Api(value = "系统管理-菜单管理API")
@RestController
@RequestMapping("/manage/menu")
public class SysMenuManageController {
    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private ISysMenuTypeService sysMenuTypeService;

    @ApiOperation(value = "1.分类树", httpMethod = "POST")
    @PostMapping("/menuTypeTree")
    public CommonResp<List<MenuTypeTree>> menuTypeTree() throws Exception{
        return new CommonResp<>(sysMenuTypeService.menuTypeTree());
    }

    @ApiOperation(value = "2.查询菜单树", httpMethod = "POST")
    @PostMapping(value = "/query")
    public CommonResp<List<MenuTree>> query(@RequestBody @Valid MenuQueryReq req){
        Long menuTypeId = req.getMenuTypeId();
        if(menuTypeId == null || menuTypeId == 0L)
            menuTypeId = null;
        return new CommonResp<>(sysMenuService.query(menuTypeId));
    }

    @ApiOperation(value = "3.新增菜单", httpMethod = "POST")
    @PostMapping("/add")
    public CommonResp<Boolean> add(@RequestBody @Valid MenuMangeAddReq menuMangeAddReq) throws CommonException {
        return new CommonResp<>(sysMenuService.add(menuMangeAddReq));
    }

    @ApiOperation(value = "4.修改菜单", httpMethod = "POST")
    @PostMapping("/update")
    public CommonResp<Boolean> update(@RequestBody @Valid MenuMangeAddReq menuMangeAddReq) throws CommonException {
        return new CommonResp<>(sysMenuService.update(menuMangeAddReq));
    }

    @ApiOperation(value = "5.删除菜单", httpMethod = "GET")
    @GetMapping("/del")
    public CommonResp<Boolean> del(@RequestParam(name = "id", required = true) @Valid Long id){
        return new CommonResp<>(sysMenuService.del(id));
    }

    @ApiOperation(value = "6.菜单上移", httpMethod = "GET")
    @GetMapping("/up")
    public CommonResp<Boolean> up(@RequestParam(name = "id", required = true) @Valid Long id) throws CommonException {
        return new CommonResp<>(sysMenuService.up(id));
    }

    @ApiOperation(value = "7.菜单下移", httpMethod = "GET")
    @GetMapping("/down")
    public CommonResp<Boolean> down(@RequestParam(name = "id", required = true) @Valid Long id) throws CommonException {
        return new CommonResp<>(sysMenuService.down(id));
    }
}
