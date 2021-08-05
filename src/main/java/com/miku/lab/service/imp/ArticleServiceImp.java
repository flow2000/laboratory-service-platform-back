package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/9 15:33
 *@version:1.1
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.miku.lab.dao.ArticleDao;
import com.miku.lab.entity.Article;
import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.Machine_sort;
import com.miku.lab.service.ArticleService;
import com.miku.lab.util.Constant;
import com.miku.lab.util.IdUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImp implements ArticleService {


    @Autowired
    private ArticleDao articleDao;

    @Override
    public Object getAllArticle() {
        List<Article> articles = articleDao.getAllArticle();
        if (articles!=null){
            return articles;
        }else{
            return null;
        }
    }

    public Object getOneArticle(String article_code) {
        return articleDao.getOneArticle(article_code);
    }

    @Override
    public Object getPageArticle(int page, int limit) {
        PageHelper.startPage(page, limit);//开始分页
        List<Map> articleList = articleDao.getPageArticle(); //拼接sql语句
        return new PageInfo<>(articleList); //将分页结果放入pageUserList
    }

    @Override
    public Object searchArticle(String searchKey, String searchValue, int page, int limit) {
        Map<String ,Object> map = new HashMap<>();
        String [] key = searchKey.split(";");
        String [] value = searchValue.split(";");
        map.put("titleKey",key[0]);
        map.put("actorKey",key[1]);
        map.put("classKey",key[2]);

        if(value.length >= 1 && !value[0].equals("") &&!value[0].equals("null")){
            map.put("titleValue",value[0]);
        }
        if(value.length>=2 && !value[1].equals("") &&!value[1].equals("null")){
            map.put("actorValue",value[1]);
        }
        if(value.length>=3 && !value[2].equals("") &&!value[2].equals("null")) {
            map.put("classValue", value[2]);
        }
        PageHelper.startPage(page, limit);
        List<Map> articleList = articleDao.searchArticle(map);
        return new PageInfo<>(articleList);
    }

    @Override
    public int addArticle(Map<String, Object> map) {
        String article_code = IdUtil.getSixNum();
        while(getOneArticle(article_code)!=null){
            article_code = IdUtil.getSixNum();
        }
        map.put("article_code",article_code);
        return articleDao.addArticle(map);
    }

    @Override
    public int deleteArticle(Map<String, Object> map) {
        String str = (String) map.get("article_code");
        String [] arr = str.split(",");
        return articleDao.deleteArticle(arr);
    }

    @Override
    public int updateArticle(Map<String, Object> map) {
        return articleDao.updateArticle(map);
    }

    @Override
    public Object getAllArticleSort() {
        List<ArticleSort> articleSorts = articleDao.getAllArticleSort();
        if (articleSorts!=null){
            return articleSorts;
        }else{
            return null;
        }
    }

    /**
     * 分页获取仪器分类数据
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Object getPageMachineSort(String page, String limit) {
        Map<String,Object> map = new HashMap<>();
        int p = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
        int m = Integer.valueOf(limit);
        map.put("page",p);
        map.put("limit",m);
        List<ArticleSort> articleSorts = articleDao.getPageArticleSort(map); //获取仪器数据
        List<ArticleSort> allArticle = articleDao.getAllArticleSort();
        map.put("article_sorts",articleSorts);
        map.put("count",allArticle.size());
        return map;
    }

    /**
     * 获取分类详细
     * @param sortId
     * @return
     */
    @Override
    public Object getSortDetail(String sortId) {
        Map<String,Object> map = new HashMap<>();
        ArticleSort articleSort = articleDao.getSortDetailById(sortId);
        if(articleSort!=null){
            map.put("sortDetail",articleSort);
            return map;
        }else{
            map.put("sortDetail","查询失败");
            return map;
        }
    }

    @Override
    public Object updateSort(ArticleSort articleSort) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",articleSort.getId());
        map.put("sortId",articleSort.getSortId());
        map.put("sortName",articleSort.getSortName());
        map.put("remark",articleSort.getRemark());
        map.put("validStatus",articleSort.getValidStatus());
        map.put("updateTime",new Date());
        map.put("updater",articleSort.getUpdater());

        int updateSort = articleDao.updateSort(map);
        if(updateSort!=0){
            return "更新成功";
        }else{
            return "更新失败";
        }
    }


    /**
     * 添加分类
     * @param articleSort
     * @return
     */
    @Override
    public int addSort(ArticleSort articleSort) {
        Map<String,Object> map = new HashMap<>();
        map.put("sortId", IdUtil.geneId(Constant.BUSINESS_SORT));
        map.put("sortName",articleSort.getSortName());
        map.put("remark",articleSort.getRemark());
        map.put("validStatus","1");
        map.put("creater",articleSort.getCreater());
        map.put("createTime",new Date());
        ArticleSort sort = articleDao.getSortDetailById(articleSort.getSortId());
        if(sort==null){
            int addSort = articleDao.addSort(map);
            if(addSort!=0){
                return 1;
            }
        }else{
            return 0;
        }
        return 0;
    }

    @Override
    public int delSort(String sortId) {
        int delSort = articleDao.delSort(sortId);
        if(delSort!=0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public  Object searchSort(String searchKey,String searchValue,String page, String limit) {

        Map<String, Object>map = new HashMap<>();
        String key[] = searchKey.split(",");
        String value[]=searchValue.split(",");
        //逐一赋值
        for (int i = 0; i < key.length; i++) {
            if(value.length>i)
                map.put(key[i],value[i]);
            else
                map.put(key[i],"");
        }
        //设置分页
        setPageLimit(map,page,limit);
        //查找用户
        List<ArticleSort> articleSorts = articleDao.searchSort(map);
        if(articleSorts!=null){
            map.put("article_sorts",articleSorts);
            map.put("count",articleSorts.size());
            return map;
        }else{
            return null;
        }

    }

    //设置分页到map中
    public Map<String, Object>setPageLimit(Map<String, Object>map,String page,String limit){
        int p = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
        int m = Integer.valueOf(limit);
        map.put("page",p);
        map.put("limit",m);
        return map;
    }
}
