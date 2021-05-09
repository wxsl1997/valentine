package com.wxsl.valentine.alibabaprovider.controller;


import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
public class LoadBalancerController {

    @RequestMapping("/uri")
    public String uri(HttpRequest httpRequest) {
        return String.format("uri:%s", httpRequest.getURI());
    }
}
