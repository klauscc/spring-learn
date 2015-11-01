package com.kyee.framework.wechat.domain.gateway;

/**
 * @author 夏之阳
 * 创建时间：2015-08-06 14:33
 * 任务号：MOBILEDEVELOP-9900
 * 创建说明：session类型
 */

public enum SessionType {
    //创建session, 销毁session, 没有session, 更新session
    CREATE_SESSION("1"),DESTROY_SESSION("2"),NO_SESSION("3"),UPDATE_SESSION("4");

    private String sessionType;

    public String toString(){
        return sessionType;
    }

    SessionType(String value){
        sessionType = value;
    }
}
