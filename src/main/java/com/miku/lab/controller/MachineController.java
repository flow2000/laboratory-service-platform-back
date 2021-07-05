package com.miku.lab.controller;


import com.miku.lab.service.MachineService;
import com.miku.lab.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @RequestMapping(value = "/listAll")
    public ReturnResult listAll(){
        return machineService.list();
    }
}
