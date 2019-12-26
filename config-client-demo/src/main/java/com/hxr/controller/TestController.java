package com.hxr.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-26 11:58
 * @Version: 1.0
 **/
@RestController
// 开启更新效果
@RefreshScope
public class TestController {

    @Value("${address}")
    private String address;

    @Value("${author}")
    private String author;

    @Value("${email}")
    private String email;

    /**
     * 返回配置文件中的值
     */

    // http://localhost:9200/actuator/bus-refresh 刷新github更新最新配置

    @GetMapping("/value")
    @ResponseBody
    public String returnFormValue() {
        return "address: "+address+" author: "+author+" email: "+email;
    }

}
