package com.kbss.admin.cms.dao;

import com.kbss.admin.cms.entity.SysMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kbss.admin.cms.enums.RoleType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {
    List<SysMenu> menuByUserId(@Param("userId") Long userId,@Param("roleType") RoleType roleType);

    Integer maxPosByPid(@Param("parentId") Long parentId);
}
