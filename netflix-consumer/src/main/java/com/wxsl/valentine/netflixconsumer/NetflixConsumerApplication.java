package com.wxsl.valentine.netflixconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
public class NetflixConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetflixConsumerApplication.class, args);
    }
}
