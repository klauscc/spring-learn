package com.kyee.framework.security.service;

import com.kyee.framework.web.ResponseHelper;
import com.kyee.framework.security.service.token.ITokenService;
import com.kyee.framework.web.Result;
import com.kyee.framework.web.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 程峰
 * 创建时间：15/8/14 下午4:20
 * 任务号：MOBILEDEVELOP-10006
 * 创建说明：授权服务
 */
@Service
public class TokenAuthenticationService {

    @Autowired
    private UserDetailsService userDetailsService;

    private UserDetailsChecker userDetailsChecker = new AccountStatusUserDetailsChecker();

    /**
     * 请求头信息中的token
     * */
    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    @Autowired
    private ITokenService tokenService;

    /**
     * 登陆时登陆成功返回授权信息并在响应头中和body中输出token
     * @param request 请求
     * @param response 响应
     * @param authentication 授权信息
     * @return 授权信息
     * @throws IOException 获取response输出流失败
     */
    public Authentication loginAuthentication(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException {

        String token = tokenService.parseTokenFromUser(authentication.getName(), response);
        response.addHeader(AUTH_HEADER_NAME, token);
        Result result = ResultHelper.newSuccessResult(token);
        ResponseHelper.createResponse(response, result);
        final UserDetails authenticatedUser = userDetailsService.loadUserByUsername(authentication.getName());
        return this.createSuccessfulAuthentication(authenticatedUser);
    }

    /**
     * 通过token授权时获取授权信息
     * @param request 请求
     * @param response 响应
     * @return 授权信息
     * @throws IOException 读取response输出流失败
     */
    public Authentication getAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String token = request.getParameter("token");   //url中获取token
        if(null == token){
            token = request.getHeader(AUTH_HEADER_NAME); //从request header中获取token
            if(null == token){ //没有获取到token
                return null;
            }
        }

        UserDetails user = tokenService.parseUserFromToken(token,response);

        if(null == user){ //根据token 解析出的UserDetails为空
            return null;
        }

        this.userDetailsChecker.check(user);
        return this.createSuccessfulAuthentication(user);
    }

    /**
     * 创建成功授权信息
     * @param user 用户信息
     * @return 授权信息
     */
    protected Authentication createSuccessfulAuthentication(UserDetails user) {
        return new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
    }

}
