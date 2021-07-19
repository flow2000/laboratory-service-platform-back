package com.miku.lab.service.imp;

import com.miku.lab.dao.SystemConfigDao;
import com.miku.lab.service.SystemConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SystemConfigServiceImp implements SystemConfigService {

    @Autowired
    private SystemConfigDao systemConfigDao;

    @Override
    public List<Map> getAllSystemConfig() {
        return systemConfigDao.getAllSystemConfig();
    }
}
