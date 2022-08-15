package com.wxsl.valentine.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubboConsumerApplication {

    static {
        // 替代 vm options, dubbo.application.logger=slf4j
        System.setProperty("dubbo.application.logger", "slf4j");
    }

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }
}
