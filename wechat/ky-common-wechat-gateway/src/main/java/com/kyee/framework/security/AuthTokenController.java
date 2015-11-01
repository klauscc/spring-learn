package com.kyee.framework.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程峰
 * 创建时间:15/9/6 上午10:43
 * 任务号:MOBILEDEVELOP-10281
 * 创建说明: 获取中间件token
 */

@RestController
public class AuthTokenController {

    /**
     * @api {get} /authToken 获取中间件token
     * @apiName authTokenController
     * @apiGroup authToken
     * @apiVersion 0.1.0
     *
     * @apiParam {String} username 用户名
     * @apiParam {String} password 密码
     *
     * @apiSuccess (返回值) {Boolean} success 是否成功
     * @apiSuccess (返回值) {String} value 返回的token内容
     * @apiSuccess (返回值) {String} state 状态
     * @apiSuccess (返回值) {String} message 失败备注消息
     * @apiSuccessExample {json} 成功返回:
     *  {
     *      "success" : true,
     *      "value" : "ZXlKMWMyVnlibUZ0WlNJNkltdDVaV1VpTENKaGRYUm9iM0pwZEdsbGN5STZXM3NpWVhWM",
     *      "state" : 0,
     *      "message" : ""
     *  }
     * @apiErrorExample {json} 失败返回:
     *  {
     *      "success":false,
     *      "value":null,
     *      "state":1,
     *      "message":"用户名或密码错误"
     *  }
     */

    /**
     * 这个controller被security的 filter拦截
     * 配置filter
     * @see com.kyee.framework.security.SecurityConfig configure(HttpSecurity http)
     * filter实现
     * @see com.kyee.framework.security.domain.filter.LoginFilter
     */
    @RequestMapping(value = "/authToken",method = RequestMethod.GET)
    public void  getAuthToken(String username,String password){

    }
}
