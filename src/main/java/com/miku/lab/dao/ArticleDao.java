package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/9 15:35
 *@version:1.1
 */

import com.miku.lab.entity.Article;
import com.miku.lab.entity.ArticleSort;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleDao {

    List<Article> getAllArticle();

    List<ArticleSort>getAllArticleSort();

}
