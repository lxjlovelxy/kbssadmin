package com.kbss.admin.cms.controller;

import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.manage.*;
import com.kbss.admin.cms.controller.resp.CommonResp;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.manage.DataDicResp;
import com.kbss.admin.cms.controller.resp.manage.DataDicTree;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.CommonContext;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.CommonSession;
import com.kbss.admin.cms.service.ISysDataDicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "系统管理-基础配置API")
@RestController
@RequestMapping("/manage/base")
public class SysBaseManageController {

    @Autowired
    private ISysDataDicService sysDataDicService;

    @ApiOperation(value = "1.分类树", httpMethod = "POST")
    @PostMapping("/dicTree")
    public CommonResp<List<DataDicTree>> dicTree() throws Exception{
        CommonSession commonSession = CommonContext.getSession();
        Long orgId = null;
        RoleType roleType = commonSession.getRoleType();
        if (roleType == RoleType.SCHOOL||roleType == RoleType.SITE) {
            orgId = commonSession.getOrgId();
        }
        return new CommonResp<>(sysDataDicService.dicTree(orgId));
    }

    @ApiOperation(value = "2.查询", httpMethod = "POST")
    @PostMapping("/query")
    public CommonResp<PageResp<DataDicResp>> query(@RequestBody @Valid PageReq<DataDicReq> pageReq) throws Exception{
        CommonSession commonSession = CommonContext.getSession();
        Long orgId = null;
        RoleType roleType = commonSession.getRoleType();
        if (roleType == RoleType.SCHOOL||roleType == RoleType.SITE) {
            orgId = commonSession.getOrgId();
        }

        return new CommonResp<>(sysDataDicService.query(orgId,pageReq));
    }

    @ApiOperation(value = "3.新增", httpMethod = "POST")
    @PostMapping("/add")
    public CommonResp<Boolean> add(@RequestBody @Valid DataDicAddReq req)throws CommonException {
        CommonSession commonSession = CommonContext.getSession();
        Long loginUserId = commonSession.getUserId();
        Long orgId = null;
        RoleType roleType = commonSession.getRoleType();
        if (roleType == RoleType.SCHOOL||roleType == RoleType.SITE) {
            orgId = commonSession.getOrgId();
        }
        return new CommonResp<>(sysDataDicService.add(loginUserId,orgId,req));
    }

    @ApiOperation(value = "4.更新", httpMethod = "POST")
    @PostMapping("/update")
    public CommonResp<Boolean> update(@RequestBody @Valid DataDicAddReq req)throws CommonException{
        CommonSession commonSession = CommonContext.getSession();
        Long loginUserId = commonSession.getUserId();
        Long orgId = null;
        RoleType roleType = commonSession.getRoleType();
        if (roleType == RoleType.SCHOOL||roleType == RoleType.SITE) {
            orgId = commonSession.getOrgId();
        }
        return new CommonResp<>(sysDataDicService.update(loginUserId,orgId,req));
    }

    @ApiOperation(value = "5.详情", httpMethod = "GET")
    @GetMapping("/info")
    public CommonResp<DataDicResp> info(@RequestParam(name = "dicId", required = true) @Valid Long dicId)throws CommonException{
        CommonSession commonSession = CommonContext.getSession();
        Long orgId = null;
        RoleType roleType = commonSession.getRoleType();
        if (roleType == RoleType.SCHOOL||roleType == RoleType.SITE) {
            orgId = commonSession.getOrgId();
        }
        return new CommonResp<>(sysDataDicService.info(orgId,dicId));
    }

    @ApiOperation(value = "6.删除", httpMethod = "GET")
    @GetMapping("/del")
    public CommonResp<Boolean> del(@RequestParam(name = "dicId", required = true) @Valid Long dicId)throws CommonException{
        CommonSession commonSession = CommonContext.getSession();
        Long orgId = null;
        RoleType roleType = commonSession.getRoleType();
        if (roleType == RoleType.SCHOOL||roleType == RoleType.SITE) {
            orgId = commonSession.getOrgId();
        }
        return new CommonResp<>(sysDataDicService.del(orgId,dicId));
    }


}
