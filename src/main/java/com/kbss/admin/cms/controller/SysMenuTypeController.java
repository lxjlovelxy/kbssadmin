package com.kbss.admin.cms.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.manage.DataDicAddReq;
import com.kbss.admin.cms.controller.req.manage.DataDicReq;
import com.kbss.admin.cms.controller.req.manage.SysMenuTypeAddReq;
import com.kbss.admin.cms.controller.resp.CommonResp;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.manage.DataDicResp;
import com.kbss.admin.cms.controller.resp.manage.RoleManageItemResp;
import com.kbss.admin.cms.controller.resp.manage.SysMenuTypeResp;
import com.kbss.admin.cms.controller.resp.manage.UserManageItemResp;
import com.kbss.admin.cms.entity.SysMenuType;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.CommonContext;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.CommonSession;
import com.kbss.admin.cms.service.ISysMenuTypeService;
import com.kbss.admin.cms.utils.IDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(value = "系统管理-菜单分类API")
@RestController
@RequestMapping("/manage/menuType")
public class SysMenuTypeController {

    @Autowired
    private ISysMenuTypeService sysMenuTypeService;

    @ApiOperation(value = "1.查询", httpMethod = "POST")
    @PostMapping("/query")
    public CommonResp<PageResp<SysMenuTypeResp>> query(@RequestBody @Valid PageReq<String> pageReq){
        EntityWrapper filter = new EntityWrapper();
        if (!StringUtils.isEmpty(pageReq.getQuery())){
            filter.like(SysMenuType.NAME, pageReq.getQuery());
        }
        filter.orderBy(SysMenuType.CODE);

        Page<SysMenuType> page = new Page(pageReq.getIndex(),pageReq.getSize());
        page = sysMenuTypeService.selectPage(page,filter);
        List rows = new ArrayList<SysMenuTypeResp>();
        for (SysMenuType smt:page.getRecords()){
            SysMenuTypeResp item = SysMenuTypeResp.builder()
                    .id(smt.getId())
                    .code(smt.getCode())
                    .name(smt.getName())
                    .memo(smt.getMemo())
                    .state(smt.getState())
                    .build();
            rows.add(item);
        }
        PageResp pageResp = PageResp.builder()
                .index(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .rows(rows)
                .build();
        return new CommonResp<>(pageResp);
    }

    @ApiOperation(value = "2.新增", httpMethod = "POST")
    @PostMapping("/add")
    public CommonResp<Boolean> add(@RequestBody @Valid SysMenuTypeAddReq req) {
        CommonSession commonSession = CommonContext.getSession();
        Long loginUserId = commonSession.getUserId();

        SysMenuType add = new SysMenuType();
        add.setId(IDUtils.newLongID())
                .setCode(req.getCode())
                .setName(req.getName())
                .setMemo(req.getMemo())
                .setState(req.getState())
                .setGmtCreater(loginUserId)
                .setGmtModifier(loginUserId);

        return new CommonResp<>(sysMenuTypeService.insert(add));
    }

    @ApiOperation(value = "3.更新", httpMethod = "POST")
    @PostMapping("/update")
    public CommonResp<Boolean> update(@RequestBody @Valid SysMenuTypeAddReq req){
        CommonSession commonSession = CommonContext.getSession();
        Long loginUserId = commonSession.getUserId();

        SysMenuType update = new SysMenuType();
        update.setId(req.getId())
                .setCode(req.getCode())
                .setName(req.getName())
                .setMemo(req.getMemo())
                .setState(req.getState())
                .setGmtCreater(loginUserId)
                .setGmtModifier(loginUserId);

        return new CommonResp<>(sysMenuTypeService.updateById(update));
    }

    @ApiOperation(value = "4.详情", httpMethod = "GET")
    @GetMapping("/info")
    public CommonResp<SysMenuType> info(@RequestParam(name = "id", required = true) @Valid Long id) {
        return new CommonResp<>(sysMenuTypeService.selectById(id));
    }

    @ApiOperation(value = "5.删除", httpMethod = "GET")
    @GetMapping("/del")
    public CommonResp<Boolean> del(@RequestParam(name = "id", required = true) @Valid Long id){
        return new CommonResp<>(sysMenuTypeService.deleteById(id));
    }
}
