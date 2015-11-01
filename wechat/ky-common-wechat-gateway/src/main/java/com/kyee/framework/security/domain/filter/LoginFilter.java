package com.kyee.framework.security.domain.filter;


import java.io.IOException;
import javax.servlet.FilterChain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kyee.framework.web.ResponseHelper;
import com.kyee.framework.security.service.TokenAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * @author 程峰
 * 创建时间：15/8/14 下午3:38
 * 任务号：MOBILEDEVELOP-10006
 * 创建说明：登陆获取token的filter
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter{
    private final TokenAuthenticationService tokenAuthenticationService;

    /**
     * 构造函数
     * @param urlMapping 对urlMapping 进行过滤
     * @param tokenAuthenticationService 授权服务
     * @param authManager 授权管理器
     * */
    public LoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService,
                          AuthenticationManager authManager, AuthenticationFailureHandler authenticationFailureHandler) {
        super(new AntPathRequestMatcher(urlMapping));
        this.tokenAuthenticationService = tokenAuthenticationService;
        setAuthenticationManager(authManager);
        setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    /**
     * 尝试获取授权
     * @param request servlet请求
     * @param response servlet 响应
     * @return 返回授权
     * */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if((null == username)||(null == password)){
            ResponseHelper.createFailResponse(response,"用户名或密码错误");
            return null;
        }
        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                username, password);
        return getAuthenticationManager().authenticate(loginToken);
    }

    /**
     * 授权成功后动作
     * @param request  servlet请求
     * @param response servlet响应
     * @param chain     过滤链
     * @param authentication  授权信息
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException {

        SecurityContextHolder.getContext().setAuthentication(tokenAuthenticationService.loginAuthentication(request,response,authentication));
    }
}
