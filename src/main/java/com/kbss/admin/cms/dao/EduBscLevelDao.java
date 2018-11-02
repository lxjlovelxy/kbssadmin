package com.kbss.admin.cms.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.kbss.admin.cms.controller.resp.school.LevelListResp;
import com.kbss.admin.cms.entity.EduBscLevel;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 层次表 Mapper 接口
 * </p>
 *
 * @author qrf
 * @since 2018-11-02
 */
public interface EduBscLevelDao extends BaseMapper<EduBscLevel> {
    List<LevelListResp> levelList(Pagination page, @Param("param") EduBscLevel param);
}
