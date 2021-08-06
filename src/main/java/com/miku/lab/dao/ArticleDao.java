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

    //获取所有文章
    List<Map> getAllArticle();

    List<Map> getPageArticle(int p,int m);

    int getPageArticleCount();

    Map<String, Object> getOneArticle(String article_code);

    List<Map> searchArticle(Map<String, Object> map);

    int searchArticleCount(Map<String, Object> map);

    int addArticle(Map<String, Object> map);

    int deleteArticle(String[] arr);

    int updateArticle(Map<String, Object> map);

    //获取所有文章分类
    List<ArticleSort>getAllArticleSort();
    //分页获取文章分类
    List<ArticleSort> getPageArticleSort(Map<String,Object> map);
    //通过id获取分类信息
    ArticleSort getSortDetailById(String sortId);
    //更新文章分类信息
    int updateSort(Map<String,Object>map);
    //添加文章分类
    int addSort(Map<String,Object>map);
    //删除文章分类
    int delSort(String sortId);

    List<ArticleSort> searchSort(Map<String,Object>map);            //获取查询数据

    List<ArticleSort> getSearchPageSort(Map<String,Object> map);    //获取查询所有分页数据

}
