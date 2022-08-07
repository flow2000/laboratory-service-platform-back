package com.miku.lab.service.imp;

import com.miku.lab.dao.OperationSortDao;
import com.miku.lab.entity.OperationSort;
import com.miku.lab.service.OperationSortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: panghai
 * @Date: 2022/07/03/18:01
 * @Description: 实验操作分类实现类
 */
@Slf4j
@Service
public class OperationSortServiceImpl implements OperationSortService {

    @Autowired
    private OperationSortDao operationSortDao;

    @Override
    public List<OperationSort> listOperationSort(OperationSort operationSort) {
        if (operationSort.getPage() != null && operationSort.getLimit() != null) {
            operationSort.setPage((operationSort.getPage() - 1) * operationSort.getLimit());
            operationSort.setLimit(operationSort.getLimit());
        }
        return operationSortDao.listOperationSort(operationSort);
    }

    @Override
    public int listOperationSortNumber(OperationSort operationSort) {
        return operationSortDao.listOperationSortNumber(operationSort);
    }

    @Override
    public int addOperationSort(OperationSort operationSort) {
        return operationSortDao.addOperationSort(operationSort);
    }

    @Override
    public int deleteOperationSorts(Long[] sortIds) {
        return operationSortDao.deleteOperationSorts(sortIds);
    }

    @Override
    public int updateOperationSort(OperationSort operationSort) {
        return operationSortDao.updateOperationSort(operationSort);
    }
}
