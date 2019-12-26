package com.hxr.service.hystrix;

import com.netflix.hystrix.*;

/**
 * @ClassName: UserCommand
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-20 18:00
 * @Version: 1.0
 **/
public class UserCommand extends HystrixCommand<String> {

    private String value;

    public UserCommand (String value) {
        super(Setter.withGroupKey(
            //服务分组
                HystrixCommandGroupKey.Factory.asKey("UserGroup"))
            //线程分组
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserPool"))
            //线程池配置
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withKeepAliveTimeMinutes(5)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(10000))
                        .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)));

        this.value = value;
    }

    @Override
    protected String run() throws Exception {
        String threadName = Thread.currentThread().getName();

        return threadName+ " ||" + value;
    }
}
