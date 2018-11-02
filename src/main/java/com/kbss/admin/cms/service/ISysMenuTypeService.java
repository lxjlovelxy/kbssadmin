package com.kbss.admin.cms.service;

import com.kbss.admin.cms.controller.resp.manage.MenuTypeTree;
import com.kbss.admin.cms.entity.SysMenuType;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 系统菜单分类表 服务类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
public interface ISysMenuTypeService extends IService<SysMenuType> {
    /**
     * 菜单分类树
     * @return
     */
    public List<MenuTypeTree> menuTypeTree();
}
