package com.kbss.admin.cms.dao;

import com.kbss.admin.cms.entity.SysMenu;
import com.kbss.admin.cms.entity.SysRoleMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统角色菜单表 Mapper 接口
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

    List<SysMenu> menuByRoleId(@Param("roleId") Long roleId);
}
