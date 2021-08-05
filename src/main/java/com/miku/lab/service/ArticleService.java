package com.miku.lab.service;/*
 *@author miku
 *@data 2021/7/9 15:33
 *@version:1.1
 */

import com.miku.lab.entity.ArticleSort;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ArticleService {

    Object getAllArticle();

    Object getPageArticle(int page, int limit);

    Object searchArticle(String searchKey, String searchValue, int page, int limit);

    int addArticle(Map<String, Object> map);

    int deleteArticle(Map<String, Object> map);

    int updateArticle(Map<String, Object> map);

    Object getAllArticleSort();

    Object getPageMachineSort(String page, String limit);

    Object getSortDetail(String sortId);

    Object updateSort(ArticleSort articleSort);

    int addSort(ArticleSort articleSort);

    int delSort(String sortId);

    Object searchSort(String searchKey,String searchValue,String page, String limit);
}
