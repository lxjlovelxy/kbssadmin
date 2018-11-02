package com.kbss.admin.cms.service;

import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.school.LevelAddReq;
import com.kbss.admin.cms.controller.req.school.LevelListReq;
import com.kbss.admin.cms.controller.req.school.LevelUpdateReq;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.school.LevelInfoResp;
import com.kbss.admin.cms.controller.resp.school.LevelListResp;
import com.kbss.admin.cms.entity.EduBscLevel;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 层次表 服务类
 * </p>
 *
 * @author qrf
 * @since 2018-11-02
 */
public interface IEduBscLevelService extends IService<EduBscLevel> {
    /**
     *
     * @param projectId
     * @param req
     * @return
     * @throws Exception
     */
    PageResp<LevelListResp> list(Long projectId, PageReq<LevelListReq> req)throws Exception;

    /**
     *
     * @param userId
     * @param projectId
     * @param req
     * @return
     * @throws Exception
     */
    Boolean add(Long userId, Long projectId, LevelAddReq req)throws Exception;

    /**
     *
     * @param userId
     * @param req
     * @return
     * @throws Exception
     */
    Boolean update(Long userId,Long projectId, LevelUpdateReq req)throws Exception;

    /**
     *
     * @param userId
     * @param levelId
     * @return
     * @throws Exception
     */
    Boolean del(Long userId, Long levelId)throws Exception;

    /**
     *
     * @param levelId
     * @return
     * @throws Exception
     */
    LevelInfoResp info(Long levelId)throws Exception;
}
