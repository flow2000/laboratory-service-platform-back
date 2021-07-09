package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/9 15:33
 *@version:1.1
 */

import com.miku.lab.dao.ArticleDao;
import com.miku.lab.entity.Article;
import com.miku.lab.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService {


    @Autowired
    private ArticleDao articleDao;

    @Override
    public Object getAllArticle() {
        List<Article> articles = articleDao.getAllArticle();
        if (articles!=null){
            return articles;
        }else{
            return null;
        }
    }
}
