package com.pjboy.ssy_back.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @program: ssy_back
 * @description: 实现 UserDetails 接口
 * @author: BLADE
 * @create: 2020-10-21 21:34
 **/
@Component
public class CustomUserDetails implements UserDetails {

  private static final long serialVersionUID = 1L;

  // 登录用户名
  private String username;

  // 登录密码
  private String password;

  private Collection<? extends  GrantedAuthority> authorities;

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
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
    return true;
  }
}
