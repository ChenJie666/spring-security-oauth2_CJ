package com.cj.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.servlet.annotation.MultipartConfig;
import javax.sql.DataSource;

/**
 * 验证客户端信息和授权码，存储并返回授权码
 */

@Configuration
@EnableAuthorizationServer
public class AuthoriaztionServerConfiguration extends AuthorizationServerConfigurerAdapter {

    //TODO 从容器中获取编码器
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
//    private PasswordEncoder passwordEncoder;

    //TODO 获取数据库连接对象并交由容器管理
    @Bean
    @Primary    //TODO 设置为主配置，屏蔽默认配置
    @ConfigurationProperties(prefix = "spring.datasource")  //TODO 指定配置参数
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    //TODO 设置为JdbcToken，即将token放入到数据库中，将对象交由容器管理
    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource());
    }

    //TODO 设置为JdbcClient，即从数据库中获取client信息，将对象交由容器管理
    @Bean
    public ClientDetailsService jdbcClientDetails(){
        return new JdbcClientDetailsService(dataSource());
    }

    //TODO 将获取的token存入数据库中
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
    }


    //TODO 对客户端的请求进行认证
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        /*
        //TODO 简略版：client信息写死在代码中
        clients
                .inMemory()
                .withClient("client1")   //设置客户端的id
                .secret(passwordEncoder.encode("secret1"))   //设置客户端的安全码
                .authorizedGrantTypes("authorization_code") //设置授权码模式（有四种：授权码模式，简单模式，密码模式，客户端模式）
                .scopes("app")  //设置授权范围
                .redirectUris("http://www.baidu.com"); //设置回调地址
         */
        //TODO 标准版：从数据库中获取客户端id和secret进行验证，如果验证成功，重定向到WebSecurity登录界面
        clients.withClientDetails(jdbcClientDetails());
    }
}

//TODO 验证服务器用账号密码登录时，只验证用户名和密码，不验证客户端secret。相当于重定向到验证服务器的网页，与客户端无关。
//TODO 携带code请求token时，只验证code和secret，不验证用户密码。用户不能将用户名密码存储到客户端中，只需要token和secret。