package com.miku.lab.dao;

import com.miku.lab.entity.OperationSort;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: panghai
 * @Date: 2022/07/02/22:47
 * @Description: 实验操作分类Dao
 */
@Mapper
@Repository
public interface OperationSortDao {

    /**
     * 获取实验操作分类
     * @param operationSort 搜索参数
     * @return 实验操作分类分类列表
     */
    public List<OperationSort> listOperationSort(OperationSort operationSort);

    /**
     * 获取实验操作分类搜索数量
     * @param operationSort 搜索参数
     * @return 实验操作分类列表数量
     */
    public int listOperationSortNumber(OperationSort operationSort);

    /**
     * 添加实验操作分类
     * @param operationSort 添加参数
     * @return 添加结果
     */
    public int addOperationSort(OperationSort operationSort);

    /**
     * 批量删除实验操作分类
     * @param sortIds 编号数组
     * @return 删除结果
     */
    public int deleteOperationSorts(Long[] sortIds);

    /**
     * 更新实验操作分类
     * @param operationSort 更新参数
     * @return 更新结果
     */
    public int updateOperationSort(OperationSort operationSort);
}
