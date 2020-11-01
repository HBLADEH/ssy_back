package com.pjboy.ssy_back.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @program: ssy_back
 * @description: 实现 UserDetails 接口
 * @author: BLADE
 * @create: 2020-10-21 21:34
 **/
@Component
public class CustomUserDetails implements UserDetails {
  String password; // 密码
  String username; // 用户名
  boolean accountNonExpired; // 账号是否过期
  boolean accountNonLocked; // 是否未锁定
  boolean credentialsNonExpired; // 是否未过期
  boolean enabled; // 账号是否可用
  Collection <? extends GrantedAuthority> authorities; // 用户的权限集合

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }
}
