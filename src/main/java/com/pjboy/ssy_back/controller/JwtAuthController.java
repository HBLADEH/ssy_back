package com.pjboy.ssy_back.controller;

import com.pjboy.ssy_back.exception.AjaxResponse;
import com.pjboy.ssy_back.exception.CustomException;
import com.pjboy.ssy_back.exception.CustomExceptionType;
import com.pjboy.ssy_back.service.JwtAuthService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @program: ssy_back
 * @description: jwt 权限验证控制器
 * @author: BLADE
 * @create: 2020-10-24 19:22
 **/
@RestController
public class JwtAuthController {
  @Resource
  private JwtAuthService jwtAuthService;

  @PostMapping("/authentication")
  public AjaxResponse login(@RequestBody Map<String, String> map) {
    String username = map.get("username");
    String password = map.get("password");
    if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
      return AjaxResponse.error(
              new CustomException(
                      CustomExceptionType.USER_INPUT_ERROR, "用户名密码不能为空"
              )
      );
    }
    try {
      return AjaxResponse.success(jwtAuthService.login(username, password), "登录成功!");
    } catch (CustomException e) {
      if (e.getMessage().equals("Bad credentials")) {
        e = new CustomException(CustomExceptionType.USER_INPUT_ERROR);
      }
      return AjaxResponse.error(e);
    }
  }

  @PostMapping("/refreshtoken")
  public AjaxResponse refresh(@RequestHeader("${jwt.header}") String token) {
    return AjaxResponse.success(jwtAuthService.refreshToken(token));
  }

}
