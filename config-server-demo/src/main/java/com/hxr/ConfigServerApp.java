package com.hxr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: ConfigServerApp
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-26 10:49
 * @Version: 1.0
 **/
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServerApp {

    public static void main(String[] args) {

       SpringApplication.run(ConfigServerApp.class,args);
    }
}

