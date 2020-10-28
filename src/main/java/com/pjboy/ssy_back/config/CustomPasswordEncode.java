package com.pjboy.ssy_back.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @program: ssy_back
 * @description: 自定义的密码加密方法, 实现 PasswordEncode 方法
 * @author: BLADE
 * @create: 2020-10-21 21:53
 **/
@Component
public class CustomPasswordEncode implements PasswordEncoder {
  @Override
  public String encode(CharSequence charSequence) {
    // 需要自定义加密, 目前只是 toString
    return charSequence.toString();
  }

  @Override
  public boolean matches(CharSequence charSequence, String s) {
    return encode(charSequence).equals(s);
  }
}
