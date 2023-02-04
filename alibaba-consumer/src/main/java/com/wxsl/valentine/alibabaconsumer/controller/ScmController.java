package com.wxsl.valentine.alibabaconsumer.controller;

import com.wxsl.valentine.alibabaconsumer.configuration.ScmProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wxsl.valentine.alibabaconsumer.configuration.Constants.PROVIDER_SERVICE_NAME;

@RestController
@RequestMapping("/scm")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ScmController {

    ScmProperties scmProperties;

    FeignScmController feignScmController;

    @GetMapping("scm-properties")
    public ScmProperties scmProperties() {
        return scmProperties;
    }

    @GetMapping("refresh-scope")
    private String refreshScope() {
        return feignScmController.refreshScope();
    }

    @FeignClient(name = PROVIDER_SERVICE_NAME)
    interface FeignScmController {

        @GetMapping("/scm/refresh-scope")
        String refreshScope();
    }
}
