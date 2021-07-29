package com.miku.lab.config;/*
 *@author 邓涛
 *@data 2021/7/29 15:14
 *@version:1.1
 */

import com.miku.lab.service.imp.LoginServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //SpringSecurity主要做两件事，一件是认证，一件是授权。
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();  //注册BCrypt加密类
    }

    @Bean
    UserDetailsService customUserService(){
        //注册UserDetailsService 的bean,实例化CustomUserDetailsService();
        //该类实现UserDetailsServer接口，从数据库获取用户名，密码，角色权限
        return new LoginServiceImpl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        super.configure(web);
        //configure(WebSecurity)用于影响全局安全性(配置资源，设置调试模式通过实现自定义防火墙定义拒绝请求)的配置设置。
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {       //AuthenticationManagerBuilder身份验证管理器
        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
        //把前端传来的数据加密
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {  //HTTP请求安全处理
        httpSecurity
                .authorizeRequests()
              // .antMatchers("/js/**").permitAll() //静态资源存放的位置
               // .antMatchers("/register.html","/register","/login","http://127.0.0.1:8848/laboratory-service-platform/login.html").permitAll()//登陆、注册页面，和跳转的URL， permitAll()无条件允许访问
                .antMatchers("http://127.0.0.1:8848/laboratory-service-platform/login.html").permitAll()//登陆、注册页面，和跳转的URL， permitAll()无条件允许访问
               // .antMatchers("/index.html").hasAnyRole("ADMIN","USER") //hasAnyRole()用户拥有该权限可访问

              //  .antMatchers("/UserSalary.html","/update.html","/insert.html").hasRole("ADMIN")
                .anyRequest().authenticated()  //任意匹配此url的用户需要身份验证
                .and()
                .formLogin()
                .loginPage("http://127.0.0.1:8848/laboratory-service-platform/login.html")          //未登录时跳转此页面
                .loginProcessingUrl("http://127.0.0.1:8848/laboratory-service-platform/login.html")
               // .failureUrl("/login?error")        //登陆失败时的跳转
              //  .defaultSuccessUrl("/index.html")  //登陆成功后默认跳转路径
                .permitAll()
                .and()
                .rememberMe().tokenValiditySeconds(1209600).key("mykey")//设置浏览器的记忆remember功能
                .and()
                .logout()
                .logoutSuccessUrl("")  //退出登录/login?logout
                .permitAll()
                .and()
                .csrf().disable(); //跨域保护
    }
}

