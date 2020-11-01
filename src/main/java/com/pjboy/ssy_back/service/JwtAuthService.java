package com.pjboy.ssy_back.service;

import com.pjboy.ssy_back.exception.CustomException;
import com.pjboy.ssy_back.exception.CustomExceptionType;
import com.pjboy.ssy_back.util.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: ssy_back
 * @description: Jwt权限服务层
 * @author: BLADE
 * @create: 2020-10-24 19:51
 **/
@Service
public class JwtAuthService {
  @Resource
  private AuthenticationManager authenticationManager;
  @Resource
  private UserDetailsService userDetailsService;
  @Resource
  private JwtTokenUtil jwtTokenUtil;

  /**
  * @Description: 通过用户名和密码登录
  * @Param: [username, password]
  * @return: Token
  * @Author: BLADE
  * @Date: 2020/10/26
  */
  public String login(String username, String password) throws CustomException {
    try {
      // 使用用户名和密码进行登录
      Object principal;
      Object credentials;
      UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
      Authentication authentication = authenticationManager.authenticate(upToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (AuthenticationException e) {
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, e.getMessage());
    }

    // 生成 jwt
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    return jwtTokenUtil.generateToken(userDetails);
  }

  public String refreshToken(String oldToken) {
    if (!jwtTokenUtil.isTokenExpired(oldToken)) {
      return jwtTokenUtil.refreshToken(oldToken);
    }
    return null;
  }


}
