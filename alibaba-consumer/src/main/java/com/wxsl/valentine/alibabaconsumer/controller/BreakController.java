package com.wxsl.valentine.alibabaconsumer.controller;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.wxsl.valentine.alibabaconsumer.configuration.Constants.PROVIDER_SERVICE_NAME;

@Slf4j
@RestController
@RequestMapping(value = "breaker", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BreakController {

    CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    BreakerFeignController breakerFeignController;

    RestTemplate restTemplate;

    @GetMapping(value = "/exp")
    public List<String> exp() {
        CircuitBreaker breaker = circuitBreakerFactory.create("exp");

        return IntStream
                .range(0, 10)
                .mapToObj(num -> breaker.run(breakerFeignController::exp,
                        throwable -> throwable instanceof DegradeException
                                ? "breaker exp degrade by sentinel"
                                : "breaker exp exception"
                ))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/rt")
    public List<String> rt() {

        CircuitBreaker breaker = circuitBreakerFactory.create("rt");

        return IntStream
                .range(0, 10)
                .mapToObj(num -> breaker.run(breakerFeignController::rt,
                        throwable -> throwable instanceof DegradeException
                                ? "breaker rt degrade by sentinel"
                                : "breaker rt exception"
                ))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/flow")
    public List<String> flow() {
        String url = String.format("http://%s/breaker/flow", PROVIDER_SERVICE_NAME);
        return IntStream
                .range(0, 10)
                .mapToObj(num -> restTemplate.getForObject(url, String.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/rest")
    public List<String> rest() {
        String url = String.format("http://%s/breaker/rest", PROVIDER_SERVICE_NAME);
        return IntStream
                .range(0, 10)
                .mapToObj(num -> {
                    try {
                        return restTemplate.getForObject(url, String.class);
                    } catch (Throwable throwable) {
                        return "breaker rest exception";
                    }
                })
                .collect(Collectors.toList());
    }

    @FeignClient(name = PROVIDER_SERVICE_NAME, contextId = "breakerFeignController")
    interface BreakerFeignController {

        @GetMapping("/breaker/exp")
        String exp();

        @GetMapping("/breaker/rt")
        String rt();
    }
}
