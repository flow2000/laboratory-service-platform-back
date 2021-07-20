package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/9 14:55
 *@version:1.1
 */

import com.miku.lab.entity.Suggestion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SuggestDao {

    List<Suggestion> getAllSuggestion();

    int addSuggest(Suggestion suggestion);

    List<Map> getPageSuggest(int p, int m);

    int getSuggestCount();

    List<Map> getOneSuggest(String openid);

    int deleteSuggest(String[] arr);

    List<Map> searchSuggest(Map<String, Object> map);

    int searchSuggestCount(Map<String, Object> map);
}
