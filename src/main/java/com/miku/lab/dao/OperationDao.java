package com.miku.lab.dao;

import com.miku.lab.entity.Operation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: panghai
 * @Date: 2022/07/02/22:37
 * @Description: 实验操作Dao
 */
@Mapper
@Repository
public interface OperationDao {

    /**
     * 获取实验操作
     * @param operation 搜索参数
     * @return 实验操作列表
     */
    public List<Operation> listOperation(Operation operation);

    /**
     * 获取实验操作搜索数量
     * @param operation 搜索参数
     * @return 实验操作列表数量
     */
    public int listOperationNumber(Operation operation);

    /**
     * 添加实验操作
     * @param operation 添加参数
     * @return 添加结果
     */
    public int addOperation(Operation operation);

    /**
     * 批量删除实验操作
     * @param operationIds 编号数组
     * @return 删除结果
     */
    public int deleteOperations(Long[] operationIds);

    /**
     * 更新实验操作
     * @param operation 更新参数
     * @return 更新结果
     */
    public int updateOperation(Operation operation);
}
