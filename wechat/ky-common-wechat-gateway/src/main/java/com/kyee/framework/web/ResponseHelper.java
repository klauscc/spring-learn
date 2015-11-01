package com.kyee.framework.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyee.framework.exception.KyeeRuntimeException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 程峰
 * 创建时间：15/8/14 下午4:43
 * 任务号：MOBILEDEVELOP-10006
 * 创建说明：将错误信息添加至response中
 */
public class ResponseHelper {

    /**
     * 将错误信息添加至response中
     * @param response 返回给访问者的响应
     * @param errmsg 错误信息
     * */
    public static void createFailResponse(HttpServletResponse response,String errmsg) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        Result result = ResultHelper.newFailureResult(errmsg);
        createResponse(response,result);
    }

    public static void createExceptionResponse(HttpServletResponse response,String errmsg, Exception e){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        Result result = ResultHelper.newExceptionResult(errmsg,e, ResultState.KNOWN_ERROR);
        createResponse(response,result);
    }

    public static void createResponse(HttpServletResponse response,Object msg){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        try {
            String out = new ObjectMapper().writeValueAsString(msg);
            response.getWriter().write(out);
        } catch (IOException e) {
            throw new KyeeRuntimeException("获取Response输出流错误!",e);
        }
    }
}
