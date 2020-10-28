package com.pjboy.ssy_back.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @program: ssy_back
 * @description: 登录专用类, 实现了 UserDetailsService 接口, 用户登录的时候调用的第一个类
 * @author: BLADE
 * @create: 2020-10-21 21:06
 **/
@Component
public class CustomUserDetailsService implements UserDetailsService {

  /**
  * @Description: 登录验证时, 通过 username 获取用户的权限信息并返回 UserDetails
   * 放到 Spring 的全局缓存 SecurityContextHolder 中, 以供授权器使用
  * @Param: [s]
  * @return: org.springframework.security.core.userdetails.UserDetails
  * @Author: BLADE
  * @Date: 2020/10/21
  */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    CustomUserDetails userDetails = new CustomUserDetails();
    userDetails.setUsername(username);
    userDetails.setPassword("123456");
    return userDetails;
  }
}
