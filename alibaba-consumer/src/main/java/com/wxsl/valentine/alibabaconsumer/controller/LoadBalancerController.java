package com.wxsl.valentine.alibabaconsumer.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.wxsl.valentine.alibabaconsumer.configuration.Constants.PROVIDER_SERVICE_NAME;

@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LoadBalancerController {

    RestTemplate restTemplate;

    MvcFeignController feignController;

    @GetMapping("/load-balance")
    public String loadBalance() {
        String url = String.format("http://%s/load/info", PROVIDER_SERVICE_NAME);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/feign")
    public String mvcFeign() {
        return feignController.info();
    }

    @FeignClient(name = PROVIDER_SERVICE_NAME, contextId = "mvcFeignController")
    interface MvcFeignController {

        @GetMapping("/load/info")
        String info();
    }
}
