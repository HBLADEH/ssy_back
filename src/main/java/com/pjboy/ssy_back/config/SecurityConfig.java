package com.pjboy.ssy_back.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * @program: ssy_back
 * @description: Spring Security 配置
 * 参考网址：
 *  https://blog.csdn.net/XlxfyzsFdblj/article/details/82083443
 *  https://blog.csdn.net/lizc_lizc/article/details/84059004
 *  https://blog.csdn.net/XlxfyzsFdblj/article/details/82084183
 *  https://blog.csdn.net/weixin_36451151/article/details/83868891
 * 查找了很多文件，有用的还有有的，感谢他们的辛勤付出
 * Security配置文件，项目启动时就加载了
 *
 * @author: BLADE
 * @create: 2020-10-21 18:32
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomPasswordEncode passwordEncode;

  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Autowired
  private ObjectMapper objectMapper;


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //http.csrf().disable()
    //        .formLogin()
    //        //.loginPage("/login") // 用户未登录时，访问任何资源都转跳到该路径，即登录页面
    //        .loginProcessingUrl("/login") // 登录表单form中action的地址，也就是处理认证请求的路径
    //        .usernameParameter("username") // 登录表单form中用户名输入框input的name名，不修改的话默认是username
    //        .passwordParameter("password") // 登录表单form中密码输入框input的name名，不修改的话默认是password
    //        //.defaultSuccessUrl("/index") // 登录认证成功后默认转跳的路径
    //      .and()
    //        .authorizeRequests()
    //        .antMatchers("/login").permitAll() // 不需要通过登录验证就可以被访问的资源路径
    //        .antMatchers("/index") //需要对外暴露的资源路径
    //        .hasAnyAuthority("ROLE_user", "ROLE_admin") // user角色和admin角色都可以访问
    //        //.antMatchers("/syslog","/sysuser")
    //        //.hasAnyRole("admin")  //admin角色可以访问
    //        .anyRequest().authenticated();
    http
            .authenticationProvider(authenticationProvider());
  }

  private AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    // 对默认的 UserDetailsService 进行覆盖
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncode);
    return authenticationProvider;
  }
}
