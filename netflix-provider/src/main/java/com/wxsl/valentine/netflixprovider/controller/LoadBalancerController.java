package com.wxsl.valentine.netflixprovider.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/load")
public class LoadBalancerController {

    @RequestMapping("/info")
    public String info(HttpServletRequest request) {
        return String.format("server:%s:%s", request.getLocalAddr(), request.getLocalPort());
    }
}
