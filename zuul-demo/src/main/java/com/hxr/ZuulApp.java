package com.hxr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: ZuulApp
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-25 19:26
 * @Version: 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
// 开启zuul功能
@EnableZuulProxy
@EnableSwagger2
public class ZuulApp {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApp.class,args);
    }
}
