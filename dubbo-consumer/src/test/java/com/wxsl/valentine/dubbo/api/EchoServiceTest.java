package com.wxsl.valentine.dubbo.api;

import com.wxsl.valentine.base.BaseTest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EchoServiceTest extends BaseTest {

    @DubboReference
    EchoService echoService;

    @Test
    void echo() {
        String ok = echoService.echo("ok");
        Assertions.assertEquals("ok", ok);
    }
}