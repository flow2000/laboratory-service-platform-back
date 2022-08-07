package com.miku.lab.service.imp;

import com.miku.lab.dao.OperationDao;
import com.miku.lab.entity.Operation;
import com.miku.lab.service.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: panghai
 * @Date: 2022/07/03/17:00
 * @Description: 实验操作实现类
 */
@Slf4j
@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationDao operationDao;

    @Override
    public List<Operation> listOperation(Operation operation) {
        if (operation.getPage() != null && operation.getLimit() != null) {
            operation.setPage((operation.getPage() - 1) * operation.getLimit());
            operation.setLimit(operation.getLimit());
        }
        return operationDao.listOperation(operation);
    }

    @Override
    public int listOperationNumber(Operation operation) {
        return operationDao.listOperationNumber(operation);
    }

    @Override
    public int addOperation(Operation operation) {
        return operationDao.addOperation(operation);
    }

    @Override
    public int deleteOperations(Long[] operationIds) {
        return operationDao.deleteOperations(operationIds);
    }

    @Override
    public int updateOperation(Operation operation) {
        return operationDao.updateOperation(operation);
    }
}
