package com.kbss.admin.cms.utils;


import com.kbss.admin.cms.filter.CommonContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 * <p>Created by qrf on 2018/10/25.</p>
 *
 * @author qrf
 */
@Component
public class CookieUtils {

    private static Integer timeout;

    public static String getToken(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getParameter(CommonContext.TOKEN_FLAG);
        if (StringUtils.isEmpty(token)){
            Cookie[] cookies = httpServletRequest.getCookies();
            if (cookies==null){
                return null;
            }
            for (Cookie cookie:cookies){
                if (CommonContext.TOKEN_FLAG.equals(cookie.getName())){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

    public static void addCookie(HttpServletResponse httpServletResponse, String token){
        Cookie cookie = new Cookie(CommonContext.TOKEN_FLAG, token);
        cookie.setPath("/");
        cookie.setMaxAge(timeout);
        httpServletResponse.addCookie(cookie);
    }

    public static void clearCookie(HttpServletResponse httpServletResponse){
        Cookie cookie = new Cookie(CommonContext.TOKEN_FLAG, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }

    @Value("${common.session.timeout}")
    public void setTimeout(Integer timeout) {
        CookieUtils.timeout = timeout;
    }
}
