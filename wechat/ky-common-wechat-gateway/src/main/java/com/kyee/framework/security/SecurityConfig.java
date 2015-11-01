package com.kyee.framework.security;


import com.kyee.framework.security.domain.filter.AuthenticationFilter;
import com.kyee.framework.security.domain.filter.LoginFilter;
import com.kyee.framework.security.domain.handler.KyeeAuthenticationFailureHandler;
import com.kyee.framework.security.service.TokenAuthenticationService;
import com.kyee.framework.web.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * @author 程峰
 * 创建时间：15/8/13 上午9:18
 * 任务号：MOBILEDEVELOP-10006
 * 创建说明：配置spring security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    /**
     * 配置http
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().and()
                .anonymous().and()
                .servletApi().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .headers().cacheControl().and()
                .authorizeRequests()
                //认证前允许通过.得到token
                .antMatchers(HttpMethod.GET, "/authToken").permitAll()
                .antMatchers("/doc/**").permitAll()
                .antMatchers("/wechatInterface").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(new KyeeAuthenticationEntryPoint()).
                and()

                //发送get 请求, url?username=xxx&password=xxx，返回token
                .addFilterBefore(new LoginFilter("/authToken", tokenAuthenticationService, authenticationManager(),myAuthenticationFailureHandler() ), UsernamePasswordAuthenticationFilter.class)
                //其它请求通过token认证
                .addFilterBefore(new AuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
    }

    public class KyeeAuthenticationEntryPoint implements AuthenticationEntryPoint {

        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) {
            ResponseHelper.createFailResponse(response, "token 无效");

        }
    }

    /**
     * token保存dao
     * */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    /**
     * 认证失败处理handler
     * */
    @Bean
    public org.springframework.security.web.authentication.AuthenticationFailureHandler myAuthenticationFailureHandler(){
        return new KyeeAuthenticationFailureHandler();
    }

    /**
     * UserDetailsService
     * @param authoritiesByUsernameQuery 获取用户信息查询的sql语句
     * @param usersByUsernameQuery 获取用户权限信息查询的sql语句
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService myUserDetailsService(
            @Value("${spring.security.userDetailsService.authoritiesByUsernameQuery}")
            String  authoritiesByUsernameQuery,
            @Value("${spring.security.userDetailsService.usersByUsernameQuery}")
            String usersByUsernameQuery
            ){

        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);

        jdbcDao.setAuthoritiesByUsernameQuery( authoritiesByUsernameQuery);
        jdbcDao.setUsersByUsernameQuery(usersByUsernameQuery);
        jdbcDao.setEnableGroups(false);
        return jdbcDao;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

}
