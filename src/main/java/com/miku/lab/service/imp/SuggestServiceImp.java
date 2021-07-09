package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/9 14:57
 *@version:1.1
 */

import com.miku.lab.dao.SuggestDao;
import com.miku.lab.entity.Suggestion;
import com.miku.lab.service.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestServiceImp implements SuggestService {

    @Autowired
    private SuggestDao suggestDao;

    @Override
    public Object getAllSuggestion() {
        List<Suggestion> suggestions = suggestDao.getAllSuggestion();
        if(suggestions!=null){
            return suggestions;
        }else{
            return null;
        }
    }
}
