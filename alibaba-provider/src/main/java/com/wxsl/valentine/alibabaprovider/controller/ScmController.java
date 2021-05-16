package com.wxsl.valentine.alibabaprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/scm")
public class ScmController {

    @Value(value = "${refresh-scope}")
    private String refreshScope;

    @GetMapping("refresh-scope")
    public String refreshScope() {
        return refreshScope;
    }
}
