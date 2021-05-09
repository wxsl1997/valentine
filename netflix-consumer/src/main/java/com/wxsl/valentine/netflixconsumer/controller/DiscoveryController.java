package com.wxsl.valentine.netflixconsumer.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wxsl.valentine.netflixconsumer.configuration.Constants.PROVIDER_SERVICE_NAME;

@RestController
@RequestMapping("discovery")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DiscoveryController {

    DiscoveryClient discoveryClient;

    @GetMapping("info")
    public List<ServiceInstance> info() {
        return discoveryClient.getInstances(PROVIDER_SERVICE_NAME);
    }
}
