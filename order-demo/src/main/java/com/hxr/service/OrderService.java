package com.hxr.service;

import com.hxr.service.hystrix.OrderCommand;
import com.hxr.service.hystrix.UserCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName: OrderService
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-04 19:21
 * @Version: 1.0
 **/
@Service
public class OrderService {

    @Autowired
    RestTemplate restTemplate;



    @HystrixCollapser(batchMethod = "findAll",collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds",value = "300")
    })
    public Future<String> findOne(Integer id) {
        System.out.println("被合并的请求");
        return null;
    }

    @HystrixCommand
    public List<String> findAll(List<Integer> ids) {
        System.out.println("合并请求");

        String url="http://hxr-user/getUserAll?ids={1}";

        List list = restTemplate.getForObject(url, List.class, StringUtils.join(ids, ","));

        return list;

    }







    @HystrixCommand(fallbackMethod = "userFallback")
    public String getUser(Integer id) {
        // 获取用户信息？？？
        String url = "http://hxr-user/user/{id}";
        String info = restTemplate.getForObject(url, String.class, id);
        return info;
    }


    // Hystrix请求缓存

    @CacheResult
    @HystrixCommand(commandKey = "cache-user")
    public String getUser2(Integer id,@CacheKey Long cacheKey) {
        String url = "http://hxr-user/user/{id}";
        String info = restTemplate.getForObject(url, String.class, id);
        return info;
    }

    // 清除缓存

    @CacheRemove(commandKey = "cache-user")
    @HystrixCommand
    public void clearRequestCache(@CacheKey Long cacheKey) {}


    // 添加服务器降级处理方法
    public String userFallback (Integer id) {
        return "error user fallback";
    }

    // 测试依赖隔离
    public String testPool() throws ExecutionException, InterruptedException {
        UserCommand userCommand = new UserCommand("库里");
        OrderCommand orderCommand1 = new OrderCommand("篮球");
        OrderCommand orderCommand2 = new OrderCommand("足球");
// 同步调用
        String val1 = userCommand.execute();
        String val2 = orderCommand1.execute();
        String val3 = orderCommand2.execute();
// 异步调用
//        Future<String> f1 = userCommand.queue();
//        Future<String> f2 = userCommand.queue();
//        Future<String> f3 = userCommand.queue();
 return "val1=" + val1 + "val2=" + val2 + "val3=" + val3;
//        return "f1=" + f1.get() + "f2=" + f2.get() + "f3=" + f3.get();
    }


}
