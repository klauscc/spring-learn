package com.kyee.wechat.gateway.controller;

import com.kyee.wechat.gateway.domain.businessinteraction.BusinessRequest;
import com.kyee.wechat.gateway.service.DispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 夏之阳
 * 创建时间：2015-08-05 13:36
 * 任务号：MOBILEDEVELOP-9900
 * 创建说明：中间件与业务后台接口
 */

/**
 * 修改人：程峰
 * 修改时间：2015-08-06 13:40
 * 任务号：MOBILEDEVELOP-9901
 * 修改说明：规范与业务通信接口
 * */

/**
 * 修改人：夏之阳
 * 任务号：MOBILEDEVELOP-10005
 * 修改时间：2015-08-12 16:53
 * 修改原因：添加apidoc的注释
 */

@RestController
public class BusinessController {
    @Autowired
    private DispatcherService dispatcherService;

    /**
     * @api {post} /businessInterface 业务端通信接口
     * @apiName BusinessController
     * @apiGroup Business
     * @apiVersion 0.1.0
     *
     * @apiParam {Json} request 请求推送消息内容
     * @apiParamExample {json} 客服消息样例：
     *  {
     *      "type": "1",                                    //业务端发送消息类型 客服消息 = 1，模板消息 = 2
     *      "session": "1",                                 //Session状态 创建Session = 1,销毁Session = 2,不创建Session = 3
     *      "openId": "o0nipuF_SKHk4oOa9fV4iyZtehrY",       //用户openId
     *      "liveTime": "1800",                             //session生存时间
     *      "content": {                                    //微信要求的客服消息格式（json格式数据），以文本消息为例
     *          "touser":"o0nipuF_SKHk4oOa9fV4iyZtehrY",
     *          "msgtype":"text",
     *           "text":
     *           {
     *              "content":"Hello World"
     *           }
     *      }
     *  }
     * @apiParamExample {json} 模板消息样例：
     *  {
     *      "type": "2",                                    //业务端发送消息类型 客服消息 = 1，模板消息 = 2
     *      "session": "3",                                 //模板消息不创建session，默认为"3"
     *      "openId": "o0nipuF_SKHk4oOa9fV4iyZtehrY",       //用户openId
     *      "liveTime": "0",                                //session生存时间为0，和session为3对应
     *      "content": {json}                               //微信要求的模板消息格式（json格式数据）
     *  }
     *
     * @apiSuccess (返回值) {Integer} errcode 错误码
     * @apiSuccess (返回值) {String} errmsg 错误信息
     * @apiSuccess (返回值) {String} msgid 消息编号
     * @apiSuccessExample {json} 推送成功样例:
     *  {
     *      "errcode" : 0,
     *      "errmsg" : "ok",
     *      "msgid" : "200228332"
     *  }
     * @apiErrorExample {json} 推送失败样例:
     *  {
     *      "errcode" : 40001,
     *      "errmsg" : "access_token无效",
     *  }
     */

    /**
     * 与C端通信接口
     *
     * @param request C端发过来的数据
     * @return 数据处理返回值
     */
    @RequestMapping(value = "/businessInterface",method = RequestMethod.POST)
    public String wechatDispatcher(@RequestBody BusinessRequest request){
        return dispatcherService.businessDispacher(request);
    }
}
