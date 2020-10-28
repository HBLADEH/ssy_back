package com.pjboy.ssy_back.controller;

import com.pjboy.ssy_back.exception.AjaxResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: ssy_back
 * @description:
 * @author: BLADE
 * @create: 2020-10-28 23:37
 **/
@RestController
public class HelloController {
  @GetMapping("/hello")
  public AjaxResponse hello() {
    return AjaxResponse.success();
  }
}
