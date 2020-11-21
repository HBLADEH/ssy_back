package com.pjboy.ssy_back.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Article)表实体类
 *
 * @author makejava
 * @since 2020-10-29 00:51:25
 */
@SuppressWarnings("serial")
@Data
public class Article extends Model<Article> {
  //ID
  private Integer id;
  //文章名称
  private String title;
  //文章关键字列表
  private String keyList;
  //简介
  private String introduction;
  //文章内容id
  private Integer articleInfoId;
  //文章时间
  private Date newsTime;
  //添加时间
  private Date addTime;

  /**
   * 获取主键值
   *
   * @return 主键值
   */
  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}