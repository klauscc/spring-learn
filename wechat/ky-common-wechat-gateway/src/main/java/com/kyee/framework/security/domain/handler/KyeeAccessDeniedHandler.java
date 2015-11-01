package com.kyee.framework.security.domain.handler;

import com.kyee.framework.web.ResponseHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 程峰
 *         创建时间:15/9/7 下午2:54
 *         任务号:
 *         创建说明:
 */
public class KyeeAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseHelper.createFailResponse(response, "token 无效");
    }
}
