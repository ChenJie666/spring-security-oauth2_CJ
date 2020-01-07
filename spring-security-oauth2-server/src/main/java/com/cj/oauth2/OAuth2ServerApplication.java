package com.cj.oauth2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.cj.oauth2.server.mapper")
public class OAuth2ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2ServerApplication.class,args);
    }
}

//TODO 启动后访问http://localhost:8080/oauth/authorize?client_id=client1&response_type=code 进行认证
//TODO 认证通过后会向设置的重定向页返回code
//TODO 将grant_type=authorization_code,code=F1hXDq作为POST参数，访问http://client1:secret1@localhost:8080/oauth/token

//TODO 返回access_token，结果如下
/*{
        "access_token": "2713daa9-a2eb-449f-becd-706a973bf289",
        "token_type": "bearer",
        "expires_in": 43199,
        "scope": "app"
        }
*/

//TODO 如果再次携带code发送请求，会报错如下
/*
{
    "error": "invalid_grant",
    "error_description": "Invalid authorization code: F1hXDq"
}
*/


    /*
    * 梳理一下整个流程：
    * 1.在第三方app上点击授权登陆后，将用户id重定向到认证服务器，输入账号密码进行验证
    * 2.账号密码经过验证正确后，返回是否授权页面
    * 3.确认授权后认证服务器重定向到设置的网址，并携带code值
    * 4.携带授权模式和code的值，访问指定的地址，返回access_token
    * 5.携带access_token访问资源服务器
    *
    * */
