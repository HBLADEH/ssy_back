package com.pjboy.ssy_back.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: ssy_back
 * @description: 登录专用类, 实现了 UserDetailsService 接口, 用户登录的时候调用的第一个类
 * @author: BLADE
 * @create: 2020-10-21 21:06
 **/
@Component
public class CustomUserDetailsService implements UserDetailsService {

  @Resource
  private CustomUserDetailsServiceMapper userDetailsServiceMapper;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 获取用户信息
    CustomUserDetails userDetails = userDetailsServiceMapper.findByUsername(username);
    if (userDetails == null) {
      throw new UsernameNotFoundException("用户名不存在");
    }

    // 获得用户角色列表
    List<String> roleCodes = userDetailsServiceMapper.findRoleByUserName(username);

    // 通过角色列表获取权限列表
    List<String> authorities  = userDetailsServiceMapper.findAuthorityByRoleCodes(roleCodes);

    // 为用户标识加上 ROLE_ 前缀 (此为 spring security 规范)
    roleCodes = roleCodes.stream()
            .map(rc -> "ROLE_" + rc)
            .collect(Collectors.toList());

    // 角色是一种特殊的权限, 所以合并
    authorities.addAll(roleCodes);

    // 转换成用逗号分隔的字符串, 为用户设置权限标识
    userDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(
            String.join(",", authorities)
    ));
    return userDetails;
  }
}
