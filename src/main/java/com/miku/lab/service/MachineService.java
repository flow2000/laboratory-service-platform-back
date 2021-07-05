package com.miku.lab.service;


import com.miku.lab.vo.ReturnResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MachineService {
    ReturnResult list();
}
