package com.wxsl.valentine.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubboProviderApplication {

    static {
        // 替代 vm options, dubbo.application.logger=slf4j
        System.setProperty("dubbo.application.logger", "slf4j");
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DubboProviderApplication.class, args);

        // 主线程等待
        synchronized (DubboProviderApplication.class){
            DubboProviderApplication.class.wait();
        }
    }
}
