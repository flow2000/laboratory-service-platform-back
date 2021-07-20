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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public int addSuggest(Suggestion suggestion) {
        suggestion.setCreateTime(new Date());
        int addAffect = suggestDao.addSuggest(suggestion);
        if(addAffect>0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Object getPageSuggest(String page, String limit) {
        Map<String,Object> map = new HashMap<>();
        int p = 0;
        int m = 10;
        try{
            p = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
            m = Integer.parseInt(limit);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","参数错误");
            return map;
        }
        List<Map> pageSuggestList = suggestDao.getPageSuggest(p,m);
        int count = suggestDao.getSuggestCount();
        map.put("suggestions",pageSuggestList);
        map.put("count",count);
        return map;
    }

    @Override
    public Object getOneSuggest(String openid) {
        if (openid==null||"".equals(openid)){
            return null;
        }
        List<Map> list = suggestDao.getOneSuggest(openid);
        return list;
    }

    @Override
    public int deleteSuggest(Map<String, Object> param) {
        String str = (String) param.get("id");
        String[] arr = str.split(",");
        return suggestDao.deleteSuggest(arr);
    }

    @Override
    public Object searchSuggest(int page, int limit, String searchKey, String searchValue) {
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("searchKey",searchKey);
        map.put("searchValue",searchValue);
        List<Map> pageUserList = suggestDao.searchSuggest(map);
        int count = pageUserList.size();
        Map<String ,Object> resMap = new HashMap<String ,Object>();
        resMap.put("suggestions",pageUserList.subList((page-1)*limit,page*limit));
        resMap.put("count",count);
        return resMap;
    }
}
