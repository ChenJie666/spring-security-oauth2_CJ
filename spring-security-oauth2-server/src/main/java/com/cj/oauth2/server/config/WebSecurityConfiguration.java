package com.cj.oauth2.server.config;

import com.cj.oauth2.server.config.service.UserDetailsServiceImpl;
import com.cj.oauth2.server.service.impl.TbPermissionServiceImpl;
import com.cj.oauth2.server.service.impl.TbUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 验证用户信息，验证用户权限
 */

@Configuration  //TODO 这是一个配置类
@EnableWebSecurity  //TODO 启动授权服务
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)  //TODO 设置全局的请求拦截
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();//TODO 支持多重加密方式，会根据自动识别数据库中的密码加密方式并转换为自己的加密方式
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public TbUserServiceImpl tbUserService(){
        return new TbUserServiceImpl();
    }

    @Bean
    public TbPermissionServiceImpl tbPermissionService(){
        return new TbPermissionServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        //TODO 简化版：因为没有设置数据库，所以账号密码写死在内存中。如果账号密码正确且确认授权，则重定向到指定url并返回授权码code
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("123456")).roles("USER");
         */
        //TODO 标准版：通过查询数据库，获取用户信息和用户权限信息
        auth.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth/check_token");
    }
}