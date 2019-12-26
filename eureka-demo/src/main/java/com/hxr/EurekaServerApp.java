package com.hxr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName: EurekaServerApp
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-04 16:59
 * @Version: 1.0
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp {

    public static void main(String[] args) {

        SpringApplication.run(EurekaServerApp.class,args);
    }
}
