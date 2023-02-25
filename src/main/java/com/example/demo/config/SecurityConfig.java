package com.example.demo.config;

import com.example.demo.filter.JwtAuthenticationTokenFilter;
import com.example.demo.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @Author: hqf
 * @Date: 2023/2/22 10:10
 */
@Configuration
@EnableWebSecurity    // 添加 security 过滤器
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启权限配置
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    AccessDeniedHandler accessDeniedHandler;

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;

    /**
     * 密码明文加密方式配置
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                // 基于 token，不需要 csrf
                .csrf().disable()
                // 基于 token，不需要 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 配置校验器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 异常处理
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint).and()
                // 认证成功、失败处理，没有使用 form表单登录使用是无效的，目前使用前后端分离配置无效
//                .formLogin().successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler).and()
                // 注销
//                .logout().logoutSuccessHandler(logoutSuccessHandler).and()
                //认证成功处理器
                // 设置权限
                .authorizeRequests(authorize -> authorize
                                // 登录接口开启匿名访问
                                .antMatchers("/user/login").anonymous()
//                        // 这里也可以设置接口权限
//                        .antMatchers("/test2").hasAuthority("menu:main")
                                // 其他地址的访问均需验证权限
                                .anyRequest().authenticated()
                )
                .build();
    }

}
