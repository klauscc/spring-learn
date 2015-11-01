package com.kyee.framework.wechat;

import com.kyee.framework.wechat.domain.wechat.event.ClickMsg;
import com.kyee.framework.wechat.domain.wechat.event.LocateMsg;
import com.kyee.framework.wechat.domain.wechat.event.SubscribeMsg;

/**
 * @author 程峰
 * 创建时间:15/8/19 下午5:12
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明:微信服务器发送过来事件消息处理handler
 */

public interface IEventMsgHandler{

     /**
      * 微信端发送绑定用户信息
      * @param msg 绑定消息
      */
     void onSubscribe(SubscribeMsg msg);

     /**
      * 微信端发送用户解除绑定信息
      * @param msg 解绑消息
      */
     void onUnsubscribe(SubscribeMsg msg);

     /**
      * 微信端发送扫描信息
      * @param msg 扫描消息
      */
     void onScan(SubscribeMsg msg);

     /**
      * 微信端发送菜单点击事件信息
      * @param msg 点击菜单消息
      */
     void onClick(ClickMsg msg);

     /**
      * 微信端发送地理位置信息
      * @param msg 地理位置信息
      */
     void onLocate(LocateMsg msg);
}
