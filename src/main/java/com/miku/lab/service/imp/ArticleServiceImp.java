package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/9 15:33
 *@version:1.1
 */

import com.miku.lab.dao.ArticleDao;
import com.miku.lab.entity.Article;
import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.Machine_sort;
import com.miku.lab.service.ArticleService;
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
        int m = Integer.valueOf(limit)*(Integer.valueOf(page)-1+1);
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
        map.put("sortId",articleSort.getSortId());
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
        Map<String,Object> map = new HashMap<>();
        map.put("key",searchValue);
        map.put("value",searchKey);
        List<ArticleSort> searchSort = articleDao.searchSort(map);

        int p = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
        int m = Integer.valueOf(limit)*(Integer.valueOf(page)-1+1);
        map.put("page",p);
        map.put("limit",m);
        List<ArticleSort> articleSorts = articleDao.getSearchPageSort(map);
        if(articleSorts!=null){
            map.put("article_sorts",articleSorts);
            map.put("count",searchSort.size());
            return map;
        }else {
            return null;
        }
    }
}
