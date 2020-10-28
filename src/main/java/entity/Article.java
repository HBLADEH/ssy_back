package entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * (Article)表实体类
 *
 * @author makejava
 * @since 2020-10-29 00:51:25
 */
@SuppressWarnings("serial")
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


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getKeyList() {
    return keyList;
  }

  public void setKeyList(String keyList) {
    this.keyList = keyList;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public Integer getArticleInfoId() {
    return articleInfoId;
  }

  public void setArticleInfoId(Integer articleInfoId) {
    this.articleInfoId = articleInfoId;
  }

  public Date getNewsTime() {
    return newsTime;
  }

  public void setNewsTime(Date newsTime) {
    this.newsTime = newsTime;
  }

  public Date getAddTime() {
    return addTime;
  }

  public void setAddTime(Date addTime) {
    this.addTime = addTime;
  }

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