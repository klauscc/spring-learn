package com.kyee.wechat.gateway.domain.businessinteraction;

/**
 * @author 程峰
 * 创建时间：2015-08-07 14:11
 * 任务号：MOBILEDEVELOP-9901
 * 创建说明：业务短向微信发送的消息类型
 */

/**
 * 修改人：程峰
 * 修改时间：2015-08-12 14:58
 * 任务号:MOBILEDEVELOP-10006
 * 修改说明：创建NO_MESSAGE c端销毁session时不向用户传递任何信息时使用.
 * */
public class MessageType {
    /**
     *客服消息
     * */
    public static final String CUSTOM_MESSAGE = "1";
    /**
     * 模板消息
     * */
    public static final String TEMPLATE_MESSAGE = "2";

    /**
     * 不向用户发送消息
     * */
    public static final String NO_MESSAGE = "3";
}
