package com.zc.springbootflowmanager.controller;

@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private RuntimeService runtimeService;

    @GetMapping("/start")
    public String startProcess() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("simpleProcess");
        return "Process started with id: " + processInstance.getId();
    }
}