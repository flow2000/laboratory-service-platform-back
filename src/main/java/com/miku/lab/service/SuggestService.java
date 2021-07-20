package com.miku.lab.service;/*
 *@author miku
 *@data 2021/7/9 14:57
 *@version:1.1
 */

import com.miku.lab.entity.Suggestion;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SuggestService {

    Object getAllSuggestion();

    int addSuggest(Suggestion suggestion);

    Object getPageSuggest(String page, String limit);

    Object getOneSuggest(String openid);

    int deleteSuggest(Map<String, Object> param);

    Object searchSuggest(int page, int limit, String searchKey, String searchValue);
}
