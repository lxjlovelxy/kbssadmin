package com.kbss.admin.cms.controller;

import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.school.LevelAddReq;
import com.kbss.admin.cms.controller.req.school.LevelListReq;
import com.kbss.admin.cms.controller.req.school.LevelUpdateReq;
import com.kbss.admin.cms.controller.resp.CommonResp;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.school.LevelInfoResp;
import com.kbss.admin.cms.controller.resp.school.LevelListResp;
import com.kbss.admin.cms.filter.CommonContext;
import com.kbss.admin.cms.filter.entity.CommonSession;
import com.kbss.admin.cms.service.IEduBscLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "学校管理-基础信息API")
@RestController
@RequestMapping("/school/base")
public class LevelController {
    @Autowired
    private IEduBscLevelService eduBscLevelService;

    @ApiOperation(value = "1.层次列表", httpMethod = "POST")
    @PostMapping(value = "/query")
    public CommonResp<PageResp<LevelListResp>> list(@Valid @RequestBody PageReq<LevelListReq> req) throws Exception {
        CommonSession commonSession = CommonContext.getSession();
        Long userId = commonSession.getUserId();
        Long projectId = commonSession.getOrgId();
        return new CommonResp<>(eduBscLevelService.list(projectId,req));
    }

    @ApiOperation(value = "2.增加层次", httpMethod = "POST")
    @PostMapping(value = "add")
    public CommonResp<Boolean> add(@Valid @RequestBody LevelAddReq req) throws Exception {
        CommonSession commonSession = CommonContext.getSession();
        Long userId = commonSession.getUserId();
        Long projectId = commonSession.getOrgId();
        return new CommonResp<>(eduBscLevelService.add(userId,projectId,req));
    }

    @ApiOperation(value = "3.修改层次", httpMethod = "POST")
    @PostMapping(value = "update")
    public CommonResp<Boolean> update(@Valid @RequestBody LevelUpdateReq req) throws Exception {
        CommonSession commonSession = CommonContext.getSession();
        Long userId = commonSession.getUserId();
        Long projectId=commonSession.getOrgId();
        return new CommonResp<>(eduBscLevelService.update(userId,projectId,req));
    }

    @ApiOperation(value = "4.删除层次", httpMethod = "GET")
    @PostMapping(value = "del")
    public CommonResp<Boolean> del(@RequestParam(name = "levelId", required = true) @Valid Long levelId) throws Exception {
        CommonSession commonSession = CommonContext.getSession();
        Long userId = commonSession.getUserId();
        return new CommonResp<>(eduBscLevelService.del(userId,levelId));
    }

    @ApiOperation(value = "5.层次信息", httpMethod = "GET")
    @PostMapping(value = "info")
    public CommonResp<LevelInfoResp> info(@RequestParam(name = "levelId", required = true) @Valid Long levelId) throws Exception {
        return new CommonResp<>(eduBscLevelService.info(levelId));
    }

}
