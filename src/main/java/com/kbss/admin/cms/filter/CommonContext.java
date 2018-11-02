package com.kbss.admin.cms.filter;

import com.kbss.admin.cms.filter.entity.CommonSession;

/**
 * <p></p>
 * <p>Created by qrf on 2017/11/6.</p>
 *
 * @author qrf
 */
public class CommonContext {

    public static final String TOKEN_FLAG = "token";

    private static ThreadLocal<CommonSession> sessionPools = new ThreadLocal<>();

    public static void putSession(CommonSession commonSession){
        sessionPools.set(commonSession);
    }

    public static CommonSession getSession(){
        return sessionPools.get();
    }
}
