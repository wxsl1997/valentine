package com.wxsl.valentine.netflixprovider.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("discovery")
public class EchoController {

    @GetMapping("echo")
    public String echo(Optional<String> name) {
        return String.format("echo:%s", name.orElse(StringUtils.EMPTY));
    }
}
