package com.kyee.wechat.gateway.domain.businessinteraction;

/**
 * @author 程峰
 * 创建时间：2015-08-07 14:11
 * 任务号：MOBILEDEVELOP-9901
 * 创建说明：session类型
 */

/**
 * 修改人：夏之阳
 * 修改时间：2015-08-16 11:17
 * 任务号：MOBILEDEVELOP-10032
 * 修改说明：新增更新session
 */

public class SessionType {

    /**
     * 创建session
     * */
    public static final String CREATE_SESSION = "1";
    /**
     * 销毁session
     * */
    public static final String DESTROY_SESSION = "2";
    /**
     * 对session不进行处理
     * */
    public static final String NO_SESSION = "3";

    /**
     * 更新session
     * */
    public static final String UPDATE_SESSION = "4";

}