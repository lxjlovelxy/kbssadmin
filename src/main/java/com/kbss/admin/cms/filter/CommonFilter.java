package com.kbss.admin.cms.filter;


import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.CommonSession;
import com.kbss.admin.cms.filter.entity.ErrorEnums;
import com.kbss.admin.cms.service.ISysUserMenuService;
import com.kbss.admin.cms.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>全局过滤</p>
 * <p>Created by qrf on 2017/8/16.</p>
 */
@Component
@Slf4j
public class CommonFilter implements HandlerInterceptor {

    @Autowired
    private ISessionService iSessionService;

    @Autowired
    private ISysUserMenuService userPermissionService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //获取token
        String token = CookieUtils.getToken(httpServletRequest);
        if (token == null){
            throw new CommonException(ErrorEnums.NO_LOGIN);
        }
        //获取session
        CommonSession commonSession = iSessionService.get(token);
        if (commonSession == null){
            throw new CommonException(ErrorEnums.SESSION_TIMEOUT);
        }
        //权限校验
        String reqPath = httpServletRequest.getRequestURI();
        List<String> allowPaths = userPermissionService.getPathsBy(commonSession.getUserId(),commonSession.getRoleType());
        log.info("请求url:{}", reqPath);
        log.info("允许url:{}", allowPaths);
        //是否拥有权限
        boolean isAllow = false;
        for(String path:allowPaths) {
            if (path.contains("*")) {
                path = path.replace("*", "");
            }
            if(reqPath.startsWith(path)){
                isAllow = true;
            }
        }
        if (!isAllow){
            throw new CommonException(ErrorEnums.NO_PERMISSON);
        }
        //放入上下文
        CommonContext.putSession(commonSession);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
