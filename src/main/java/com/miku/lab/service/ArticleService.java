package com.miku.lab.service;/*
 *@author miku
 *@data 2021/7/9 15:33
 *@version:1.1
 */

import com.miku.lab.entity.ArticleSort;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService {

    Object getAllArticle();

    Object getAllArticleSort();

    Object getPageMachineSort(String page, String limit);

    Object getSortDetail(String sortId);

    Object updateSort(ArticleSort articleSort);

    int addSort(ArticleSort articleSort);

    int delSort(String sortId);

    Object searchSort(String searchKey,String searchValue);
}
