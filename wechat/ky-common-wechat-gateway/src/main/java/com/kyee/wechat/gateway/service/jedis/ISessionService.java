package com.kyee.wechat.gateway.service.jedis;

/**
 * @author 程峰
 * 创建时间：15/8/5 下午1:15
 * 任务号：MOBILEDEVELOP-9901
 * 创建说明：管理session服务接口
 */

/**
 * 修改人：夏之阳
 * 修改时间：2015/8/14 15:00
 * 任务号：MOBILEDEVELOP-10032
 * 修改说明：改用hash表存储数据，添加content字段
 * 修改说明：新增重置剩余时间接口
 */

public interface ISessionService {

    /**
     * 新建session
     * @param openId 用户微信openId
     * @param expire 该session存活时间，单位为秒
     * */
    void createSession(String openId, long expire);

    /**
     * 添加字符串字段，使之可以缓存用户回复数据
     *
     * @param openId 用户微信openId
     * @param key 字段的key值
     * @param content 字段内容
     */
    void setContent(String openId,String key,Object content);

    /**
     * 获取缓存数据
     *
     * @param openId 用户openId
     * @param key 字段的key值
     * @return key对应的字段
     */
    Object getContent(String openId,String key);

    /**
     * 重置剩余时间
     *
     * @param openId 用户微信openId
     * @param expire 剩余时间
     */
    void setExpire(String openId, long expire);

    /**
     * 删除session
     * @param  openId 用户微信openId
     * */
    void destroySession(String openId);

    /**
     * 判断用户的session是否有效
     * @param openId 用户微信openId
     * @return 有效返回true,无效返回false
     * */
    boolean isSessionValid(String openId);

    /**
     * 获取用户session的剩余存活时间
     * @param openId 用户微信openId
     * @return 返回剩余存活时间，单位为秒
     * */
    long getExpire(String openId);

    /**
     * 获取用户session创建时间
     * @param openId 用户微信openId
     * @return unix时间戳
     * */
    String getSessionCreateTimestamp(String openId);
}
