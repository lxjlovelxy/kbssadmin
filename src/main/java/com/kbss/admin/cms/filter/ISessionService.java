package com.kbss.admin.cms.filter;


import com.kbss.admin.cms.filter.entity.CommonSession;

/**
 * <p></p>
 * <p>Created by qrf on 2018/10/25.</p>
 *
 * @author qrf
 */
public interface ISessionService {

    public void set(CommonSession commonSession) throws Exception;

    public CommonSession get(String token) throws Exception;

    public void remove(String token) throws Exception;
}
