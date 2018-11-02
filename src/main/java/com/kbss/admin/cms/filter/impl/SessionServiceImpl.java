package com.kbss.admin.cms.filter.impl;

import com.kbss.admin.cms.filter.ISessionService;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.CommonSession;
import com.kbss.admin.cms.filter.entity.ErrorEnums;
import com.kbss.admin.cms.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 * <p>Created by qrf on 2018/10/25.</p>
 *
 * @author qrf
 */
@Service
public class SessionServiceImpl implements ISessionService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${common.session.timeout}")
    private Integer timeout;

    @Override
    public void set(CommonSession commonSession) throws Exception {
        this.set(commonSession.getToken(),commonSession, timeout);
    }

    @Override
    public CommonSession get(String token) throws Exception {
        CommonSession commonSession = this.get(token,CommonSession.class);
        if (commonSession == null){
            throw new CommonException(ErrorEnums.SESSION_TIMEOUT);
        }
        this.set(commonSession);
        return commonSession;
    }

    @Override
    public void remove(String token) throws Exception {
        redisTemplate.delete(token);
    }

    private void set(String token, Object session, long seconds) throws Exception {
        redisTemplate.opsForValue().set(token, JsonUtils.toJSONString(session),seconds, TimeUnit.SECONDS);
    }

    private  <T> T get(String token, Class<T> clazz) throws Exception {
        return JsonUtils.parseObject(redisTemplate.opsForValue().get(token),clazz);
    }
}
