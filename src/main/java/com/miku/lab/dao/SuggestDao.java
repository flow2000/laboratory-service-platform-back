package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/9 14:55
 *@version:1.1
 */

import com.miku.lab.entity.Suggestion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SuggestDao {

    List<Suggestion> getAllSuggestion();
}
