package com.wxsl.valentine.netflixconsumer.controller;

import com.netflix.loadbalancer.AbstractLoadBalancer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.wxsl.valentine.netflixconsumer.configuration.Constants.PROVIDER_SERVICE_NAME;

@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LoadBalancerController {

    RestTemplate restTemplate;

    SpringClientFactory springClientFactory;

    @GetMapping("/load-balance")
    public String loadBalance() {
        String url = String.format("http://%s/load/info", PROVIDER_SERVICE_NAME);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/stats")
    public String stats() {
        AbstractLoadBalancer loadBalancer = springClientFactory.getInstance(PROVIDER_SERVICE_NAME, AbstractLoadBalancer.class);
        return loadBalancer.getLoadBalancerStats().toString();
    }
}
