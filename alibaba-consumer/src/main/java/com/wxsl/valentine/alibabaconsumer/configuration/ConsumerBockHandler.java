package com.wxsl.valentine.alibabaconsumer.configuration;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

@Slf4j
@SuppressWarnings("unused")
public class ConsumerBockHandler {

    public static SentinelClientHttpResponse blockHandler(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        log.error("breaker rest block degrade by sentinel");
        return new SentinelClientHttpResponse("breaker rest block degrade by sentinel");
    }

    public static SentinelClientHttpResponse fallback(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        log.error("breaker rest fall back degrade by sentinel");
        return new SentinelClientHttpResponse("breaker rest fall back degrade by sentinel");
    }
}
