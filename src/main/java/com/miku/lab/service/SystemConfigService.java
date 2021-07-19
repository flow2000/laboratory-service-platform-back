package com.miku.lab.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SystemConfigService {

    List<Map> getAllSystemConfig();
}
