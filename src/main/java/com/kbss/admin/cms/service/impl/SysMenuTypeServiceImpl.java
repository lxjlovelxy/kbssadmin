package com.kbss.admin.cms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.kbss.admin.cms.controller.resp.manage.DataDicTree;
import com.kbss.admin.cms.controller.resp.manage.MenuTypeTree;
import com.kbss.admin.cms.entity.SysDataDic;
import com.kbss.admin.cms.entity.SysMenuType;
import com.kbss.admin.cms.dao.SysMenuTypeDao;
import com.kbss.admin.cms.service.ISysMenuTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统菜单分类表 服务实现类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Service
public class SysMenuTypeServiceImpl extends ServiceImpl<SysMenuTypeDao, SysMenuType> implements ISysMenuTypeService {

    /**
     * 菜单分类树
     *
     * @return
     */
    @Override
    public List<MenuTypeTree> menuTypeTree() {
        EntityWrapper filter = new EntityWrapper();
        filter.orderBy(SysMenuType.CODE);

        List<SysMenuType> smts = this.selectList(filter);
        List<MenuTypeTree> children = new ArrayList<>();
        for(SysMenuType smt :smts){
            MenuTypeTree item = MenuTypeTree.builder()
                    .name(smt.getName())
                    .code(smt.getCode())
                    .id(smt.getId())
                    .build();
            children.add(item);
        }

        List<MenuTypeTree> resp = new ArrayList<>();
        resp.add(MenuTypeTree.builder().children(children).name("所有").code("").id(0L).build());
        return resp;
    }
}
