package com.wxsl.valentine.alibabaconsumer.configuration;

import com.alibaba.cloud.circuitbreaker.sentinel.SentinelCircuitBreakerFactory;
import com.alibaba.cloud.circuitbreaker.sentinel.SentinelConfigBuilder;
import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Slf4j
@Configuration
public class ConsumerConfiguration {

    @Bean
    @LoadBalanced
    @SentinelRestTemplate(
            fallback = "fallback",
            blockHandler = "blockHandler",
            fallbackClass = ConsumerBockHandler.class,
            blockHandlerClass = ConsumerBockHandler.class
    )
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @ConfigurationProperties("scm")
    ScmProperties scmProperties() {
        return new ScmProperties();
    }

    @Bean
    Customizer<SentinelCircuitBreakerFactory> customizer() {

        return factory -> {

            factory.configureDefault(id -> {
                DegradeRule degradeRule = new DegradeRule(id)
                        .setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT)
                        .setCount(5)
                        .setTimeWindow(10);

                return new SentinelConfigBuilder()
                        .resourceName(id)
                        .rules(Collections.singletonList(degradeRule))
                        .build();


            });
            factory.configure(builder -> {

                DegradeRule degradeRule = new DegradeRule("slow")
                        .setGrade(RuleConstant.DEGRADE_GRADE_RT)
                        .setCount(100)
                        .setTimeWindow(10);

                builder.rules(Collections.singletonList(degradeRule));
            }, "rt");
        };
    }
}
