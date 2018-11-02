package com.kbss.admin.cms.service;

import com.kbss.admin.cms.controller.req.PageReq;
import com.kbss.admin.cms.controller.req.manage.DataDicAddReq;
import com.kbss.admin.cms.controller.req.manage.DataDicReq;
import com.kbss.admin.cms.controller.resp.PageResp;
import com.kbss.admin.cms.controller.resp.manage.DataDicResp;
import com.kbss.admin.cms.controller.resp.manage.DataDicTree;
import com.kbss.admin.cms.controller.resp.manage.UserManageItemResp;
import com.kbss.admin.cms.entity.SysDataDic;
import com.baomidou.mybatisplus.service.IService;
import com.kbss.admin.cms.enums.RoleType;
import com.kbss.admin.cms.filter.entity.CommonException;

import java.util.List;

/**
 * <p>
 * 数据字典表 服务类
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
public interface ISysDataDicService extends IService<SysDataDic> {
    /**
     * 字典树
     * @param orgId
     * @return
     * @throws CommonException
     */
    public List<DataDicTree> dicTree(Long orgId)throws CommonException;

    /**
     * 查询
     * @param orgId
     * @param pageReq
     * @return
     * @throws CommonException
     */
    public PageResp<DataDicResp> query(Long orgId,PageReq<DataDicReq> pageReq)throws CommonException;
    /**
     * 添加
     * @param orgId
     * @param req
     * @return
     * @throws CommonException
     */
    public Boolean add(Long loginUserId,Long orgId,DataDicAddReq req)throws CommonException;

    /**
     * 修改
     * @param orgId
     * @param req
     * @return
     * @throws CommonException
     */
    public Boolean update(Long loginUserId,Long orgId,DataDicAddReq req)throws CommonException;

    /**
     * 详情
     * @param orgId
     * @param dicId
     * @return
     * @throws CommonException
     */
    public DataDicResp info(Long orgId, Long dicId)throws CommonException;
    /**
     * 删除
     * @param orgId
     * @param dicId
     * @return
     * @throws CommonException
     */
    public Boolean del(Long orgId, Long dicId)throws CommonException;
}
