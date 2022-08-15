package com.wxsl.valentine.dubbo.service;

import com.wxsl.valentine.dubbo.api.EchoService;
import org.apache.dubbo.config.annotation.DubboService;


@DubboService
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String message) {
        return message;
    }
}
