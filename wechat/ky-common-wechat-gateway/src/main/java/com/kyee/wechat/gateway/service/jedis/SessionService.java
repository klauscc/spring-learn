package com.kyee.wechat.gateway.service.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 程峰
 * 创建时间：15/8/4 下午4:21
 * 任务号：MOBILEDEVELOP-9901
 * 创建说明：管理session的服务：新建session, 判断session是否存在，获取session创建时间
 */

/**
 * 修改人：夏之阳
 * 修改时间：2015/8/14 15:00
 * 任务号：MOBILEDEVELOP-10032
 * 修改说明：改用hash表存储数据，添加content字段
 * 修改说明：新增重置剩余时间函数
 */
@Service
public class SessionService implements ISessionService{
    @Autowired
    StringRedisTemplate redisTemplate;

    public void createSession(String openId,long expire){
        redisTemplate.opsForHash().put(openId,"currentTime",String.valueOf(System.currentTimeMillis()));
        redisTemplate.expire(openId, expire, TimeUnit.SECONDS);
    }

    public void setContent(String openId,String key,Object content){
        redisTemplate.opsForHash().put(openId, key, content);
    }

    public Object getContent(String openId,String key){
        return redisTemplate.opsForHash().get(openId,key);
    }

    public void setExpire(String openId, long expire){
        redisTemplate.expire(openId, expire, TimeUnit.SECONDS);
    }

    public void destroySession(String openId){
        redisTemplate.delete(openId);
    }

    public boolean isSessionValid(String openId){
        return (redisTemplate.opsForHash().get(openId,"currentTime") !=null);
    }

    public long getExpire(String openId){
        return redisTemplate.getExpire(openId);
    }

    public String getSessionCreateTimestamp(String openId){
        return redisTemplate.opsForHash().get(openId,"currentTime").toString();
    }
}
