package com.wxsl.valentine.base;

import com.wxsl.valentine.TestMain;
import lombok.NonNull;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(classes = {TestMain.class})
@ExtendWith(SpringExtension.class)
@TestPropertySource(value = {"classpath:config/test.properties"})
public abstract class BaseTest extends AbstractJUnit4SpringContextTests {

    static {
        // 替代 vm options, dubbo.application.logger=slf4j
        System.setProperty("dubbo.application.logger", "slf4j");
    }

    @NonNull
    public ApplicationContext applicationContext() {
        //noinspection ConstantConditions
        return applicationContext;
    }
}
