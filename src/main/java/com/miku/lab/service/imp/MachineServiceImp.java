package com.miku.lab.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miku.lab.dao.MachineDao;
import com.miku.lab.dao.Machine_imgDao;
import com.miku.lab.entity.Machine;
import com.miku.lab.entity.Machine_img;
import com.miku.lab.service.MachineService;
import com.miku.lab.util.Constant;
import com.miku.lab.util.ResultUtil;
import com.miku.lab.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineServiceImp implements MachineService {

    @Autowired
    private MachineDao machineDao;

    @Autowired
    private Machine_imgDao machineImgDao;

    @Override
    public ReturnResult list() {
        QueryWrapper<Machine> queryWrapper = new QueryWrapper<>();
        List<Machine> machines = new ArrayList<>();
        List<Object> lists = new ArrayList<>();
        List<Machine> list = machineDao.selectList(null);
        lists.add(list);
        List<Machine_img> imgs = machineImgDao.selectList(null);
        lists.add(list);
        lists.add(imgs);
        return ResultUtil.success( lists, Constant.RESCODE_SUCCESS,list.size());
    }
}
