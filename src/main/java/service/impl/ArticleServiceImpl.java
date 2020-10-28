package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dao.ArticleDao;
import entity.Article;
import org.springframework.stereotype.Service;
import service.ArticleService;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2020-10-29 00:51:27
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

}