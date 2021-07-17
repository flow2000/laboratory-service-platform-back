package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/9 15:35
 *@version:1.1
 */

import com.miku.lab.entity.Article;
import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.Machine_sort;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ArticleDao {

    List<Article> getAllArticle();

    List<ArticleSort>getAllArticleSort();

    List<ArticleSort> getPageArticleSort(Map<String,Object> map);

    ArticleSort getSortDetailById(String sortId);

    int updateSort(Map<String,Object>map);

    int addSort(Map<String,Object>map);

    int delSort(String sortId);

    List<ArticleSort> searchSort(Map<String,Object>map);            //获取查询数据

    List<ArticleSort> getSearchPageSort(Map<String,Object> map);    //获取查询所有分页数据

}
