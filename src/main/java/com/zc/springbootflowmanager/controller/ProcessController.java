package com.zc.springbootflowmanager.controller;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zengc
 * @date 2024/05/17
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Resource
    private RuntimeService runtimeService;

    @GetMapping("/start")
    public String startProcess() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("simpleProcess");
        return "Process started with id: " + processInstance.getId();
    }
}