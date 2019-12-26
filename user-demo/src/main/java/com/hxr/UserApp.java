package com.hxr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: UserApp
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-04 17:05
 * @Version: 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class UserApp {

    public static void main(String[] args) {
        SpringApplication.run(UserApp.class,args);
    }
}
