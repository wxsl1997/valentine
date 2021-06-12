package com.wxsl.valentine.alibabaprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("breaker")
public class BreakController {

    @GetMapping("/rt")
    public String rt() throws InterruptedException {
        Thread.sleep(200L);
        return "success";
    }

    @GetMapping(value = "/flow")
    public String flow() {
        return "flow";
    }
}
