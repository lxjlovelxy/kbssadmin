package com.kbss.admin.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.school.LevelAddReq;
import com.kbss.admin.cms.controller.req.school.LevelListReq;
import com.kbss.admin.cms.controller.req.school.LevelUpdateReq;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.school.LevelInfoResp;
import com.kbss.admin.cms.controller.resp.school.LevelListResp;
import com.kbss.admin.cms.entity.EduBscLevel;
import com.kbss.admin.cms.dao.EduBscLevelDao;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.ErrorEnums;
import com.kbss.admin.cms.service.IEduBscLevelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kbss.admin.cms.utils.IDUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 层次表 服务实现类
 * </p>
 *
 * @author qrf
 * @since 2018-11-02
 */
@Service
public class EduBscLevelServiceImpl extends ServiceImpl<EduBscLevelDao, EduBscLevel> implements IEduBscLevelService {

    @Override
    public PageResp<LevelListResp> list(Long projectId, PageReq<LevelListReq> req) throws Exception {

        Page page = new Page(req.getIndex(),req.getSize());
        EduBscLevel bl = new EduBscLevel();
        bl.setShortName(req.getQuery().getShortName());
        bl.setName(req.getQuery().getLevelName());
        bl.setProjectId(projectId);

        page.setRecords(this.baseMapper.levelList(page,bl));

        PageResp pageResp = PageResp.builder()
                .index(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .rows(page.getRecords())
                .build();
        return pageResp;
    }

    @Override
    public Boolean add(Long userId, Long projectId, LevelAddReq req) throws Exception {
        EntityWrapper<EduBscLevel> ent = new EntityWrapper<>();
        ent.eq(EduBscLevel.CODE, req.getLevelCode());
        ent.eq(EduBscLevel.PROJECT_ID, projectId);
        if (this.selectCount(ent) > 0) {
            throw new CommonException(ErrorEnums.DB_ADD_EXIST,req.getLevelCode());
        }

        EduBscLevel bl = new EduBscLevel();
        bl.setId(IDUtils.newLongID());
        bl.setCode(req.getLevelCode());
        bl.setShortName(req.getShortName());
        bl.setName(req.getLevelName());
        bl.setProjectId(projectId);
        bl.setState(req.getState());
        bl.setMemo(req.getMemo());
        bl.setGmtCreater(userId);
        bl.setGmtModifier(userId);

        return this.insert(bl);
    }

    @Override
    public Boolean update(Long userId, Long projectId,LevelUpdateReq req) throws Exception {
        EntityWrapper<EduBscLevel> ent = new EntityWrapper<>();
        ent.eq(EduBscLevel.CODE, req.getLevelCode());
        ent.eq(EduBscLevel.PROJECT_ID, projectId);
        ent.eq(EduBscLevel.ID,req.getLevelId());
        if (this.selectCount(ent) > 0) {
            throw new CommonException(ErrorEnums.DB_UPDATE_EXIST,req.getLevelCode());
        }

        EduBscLevel update = new EduBscLevel();
        update.setId(req.getLevelId());
        update.setCode(req.getLevelCode());
        update.setName(req.getLevelName());
        update.setShortName(req.getShortName());
        update.setState(req.getState());
        update.setMemo(req.getMemo());
        update.setGmtModifier(userId);

        return this.updateById(update);
    }

    @Override
    public Boolean del(Long userId, Long levelId) throws Exception {
        EntityWrapper ew=new EntityWrapper();
        ew.eq(EduBscLevel.ID,levelId);

        return this.delete(ew);
    }

    @Override
    public LevelInfoResp info(Long levelId) throws Exception {

        EntityWrapper ew = new EntityWrapper();
        ew.eq(EduBscLevel.ID,levelId);
        EduBscLevel resp = this.selectOne(ew);
        if(resp == null){
            return new LevelInfoResp();
        }

        return LevelInfoResp.builder()
                .levelCode(resp.getCode())
                .levelName(resp.getName())
                .levelId(resp.getId())
                .memo(resp.getMemo())
                .state(resp.getState())
                .shortName(resp.getShortName()).build();
    }
}
