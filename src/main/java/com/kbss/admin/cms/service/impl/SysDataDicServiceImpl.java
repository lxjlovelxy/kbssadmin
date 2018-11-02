package com.kbss.admin.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.manage.DataDicAddReq;
import com.kbss.admin.cms.controller.req.manage.DataDicReq;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.manage.DataDicResp;
import com.kbss.admin.cms.controller.resp.manage.DataDicTree;
import com.kbss.admin.cms.controller.resp.manage.UserManageItemResp;
import com.kbss.admin.cms.entity.SysDataDic;
import com.kbss.admin.cms.dao.SysDataDicDao;
import com.kbss.admin.cms.entity.SysRole;
import com.kbss.admin.cms.entity.UsrSys;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.ErrorEnums;
import com.kbss.admin.cms.service.ISysDataDicService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kbss.admin.cms.utils.IDUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据字典表 服务实现类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Service
public class SysDataDicServiceImpl extends ServiceImpl<SysDataDicDao, SysDataDic> implements ISysDataDicService {

    /**
     * 字典树
     *
     * @param orgId
     * @return
     * @throws CommonException
     */
    @Override
    public List<DataDicTree> dicTree(Long orgId) throws CommonException {
        EntityWrapper filter = new EntityWrapper();
        filter.setSqlSelect("distinct "+SysDataDic.STYLE);
        if (orgId!=null){
            filter.eq(SysDataDic.ORG_ID, orgId);
        }else{
            filter.isNull(SysDataDic.ORG_ID);
        }


        filter.orderBy(SysDataDic.CODE);

        List<SysDataDic> dics = this.selectList(filter);
        List<DataDicTree> children = new ArrayList<>();
        for(SysDataDic dic :dics){
            DataDicTree item = DataDicTree.builder()
                    .style(dic.getStyle()).build();
            children.add(item);
        }

        List<DataDicTree> resp = new ArrayList<>();
        resp.add(DataDicTree.builder().children(children).style("所有").build());
        return resp;
    }

    /**
     * 查询
     *
     * @param orgId
     * @param pageReq
     * @return
     * @throws CommonException
     */
    @Override
    public PageResp<DataDicResp> query(Long orgId, PageReq<DataDicReq> pageReq) throws CommonException {
        String style = pageReq.getQuery().getStyle();
        String code = pageReq.getQuery().getCode();

        EntityWrapper filter = new EntityWrapper();
        if (!StringUtils.isEmpty(style)){
            filter.eq(SysDataDic.STYLE, style);
        }
        if (!StringUtils.isEmpty(code)){
            filter.like(SysDataDic.CODE, code);
        }

        Page<SysDataDic> page = new Page(pageReq.getIndex(),pageReq.getSize());
        page = this.selectPage(page,filter);
        List rows = new ArrayList<DataDicResp>();
        for (SysDataDic dic:page.getRecords()){
            DataDicResp item = DataDicResp.builder()
                    .id(dic.getId())
                    .style(dic.getStyle())
                    .code(dic.getCode())
                    .name(dic.getName())
                    .value(dic.getValue())
                    .value2(dic.getValue2())
                    .value3(dic.getValue3())
                    .memo(dic.getMemo())
                    .memo(dic.getMemo2())
                    .memo(dic.getMemo3())
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
     * 添加
     *
     * @param orgId
     * @param req
     * @return
     * @throws CommonException
     */
    @Override
    public Boolean add(Long loginUserId,Long orgId, DataDicAddReq req) throws CommonException {
        //判断分类编码是否已存在
        EntityWrapper filter = new EntityWrapper();
        filter.eq(SysDataDic.STYLE,req.getStyle())
                .eq(SysDataDic.CODE,req.getCode());
        if(this.selectCount(filter)>0){
            throw new CommonException(ErrorEnums.DB_ADD_EXIST,req.getCode());
        }

        SysDataDic add = new SysDataDic();
        add.setId(IDUtils.newLongID())
                .setStyle(req.getStyle())
                .setCode(req.getCode())
                .setName(req.getName())
                .setValue(req.getValue())
                .setValue2(req.getValue2())
                .setValue3(req.getValue3())
                .setMemo(req.getMemo())
                .setMemo2(req.getMemo2())
                .setMemo3(req.getMemo3())
                .setGmtCreater(loginUserId)
                .setGmtModifier(loginUserId);

        if (orgId!=null){
            add.setOrgId(orgId);
        }

        return this.insert(add);
    }

    /**
     * 修改
     *
     * @param orgId
     * @param req
     * @return
     * @throws CommonException
     */
    @Override
    public Boolean update(Long loginUserId,Long orgId, DataDicAddReq req) throws CommonException {
        //判断分类编码是否已存在
        EntityWrapper filter = new EntityWrapper();
        filter.eq(SysDataDic.STYLE,req.getStyle())
                .eq(SysDataDic.CODE,req.getCode())
                .ne(SysDataDic.ID,req.getId());
        if(this.selectCount(filter)>0){
            throw new CommonException(ErrorEnums.DB_UPDATE_EXIST,req.getCode());
        }

        SysDataDic update = new SysDataDic();
        update.setId(req.getId())
                .setStyle(req.getStyle())
                .setCode(req.getCode())
                .setName(req.getName())
                .setValue(req.getValue())
                .setValue2(req.getValue2())
                .setValue3(req.getValue3())
                .setMemo(req.getMemo())
                .setMemo2(req.getMemo2())
                .setMemo3(req.getMemo3())
                .setGmtModifier(loginUserId);

        if (orgId!=null){
            update.setOrgId(orgId);
        }

        return this.updateById(update);
    }

    /**
     * 详情
     *
     * @param orgId
     * @param dicId
     * @return
     * @throws CommonException
     */
    @Override
    public DataDicResp info(Long orgId, Long dicId) throws CommonException {
        EntityWrapper filter = new EntityWrapper();
        filter.eq(SysDataDic.ID,dicId);
        if (orgId!=null){
            filter.eq(SysDataDic.ORG_ID, orgId);
        }else{
            filter.isNull(SysDataDic.ORG_ID);
        }

        SysDataDic dic = this.selectOne(filter);
        if(dic == null){
            return new DataDicResp();
        }

        return DataDicResp.builder()
                .id(dic.getId())
                .style(dic.getStyle())
                .code(dic.getCode())
                .name(dic.getName())
                .value(dic.getValue())
                .value2(dic.getValue2())
                .value3(dic.getValue3())
                .memo(dic.getMemo())
                .memo(dic.getMemo2())
                .memo(dic.getMemo3())
                .build();
    }

    /**
     * 删除
     *
     * @param orgId
     * @param dicId
     * @return
     * @throws CommonException
     */
    @Override
    public Boolean del(Long orgId, Long dicId) throws CommonException {
        EntityWrapper filter = new EntityWrapper();
        filter.eq(SysDataDic.ID,dicId);
        if (orgId!=null){
            filter.eq(SysDataDic.ORG_ID, orgId);
        }else{
            filter.isNull(SysDataDic.ORG_ID);
        }

        return this.delete(filter);
    }
}
