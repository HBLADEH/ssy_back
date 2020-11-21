package com.pjboy.ssy_back.controller;

import com.pjboy.ssy_back.entity.Article;
import com.pjboy.ssy_back.exception.AjaxResponse;
import com.pjboy.ssy_back.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ssy_back
 * @description: 文章控制类
 * @author: BLADE
 * @create: 2020-11-20 21:23
 **/
@RequestMapping("article")
@RestController
public class ArticleController {

  @Autowired
  private ArticleMapper articleMapper;

  /**
  * @Description: 获取首页的文章
  * @Param: []
  * @return: java.util.List<com.pjboy.ssy_back.entity.Article>
  * @Author: BLADE
  * @Date: 2020/11/20
  */
  @GetMapping("main")
  public AjaxResponse getMainArticle() {
    List<Article> articleList = articleMapper.selectList(null);
    return AjaxResponse.success(articleList,"获取文章信息成功");
  }
}
