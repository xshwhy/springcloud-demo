package com.hxr.controller;

import com.hxr.service.FeignService;
import com.hxr.service.OrderService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName: OrderController
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-04 19:19
 * @Version: 1.0
 **/
@RestController
@Api(value = "订单接口")
public class OrderController {


    @Autowired
    OrderService orderService;


    @Autowired
    FeignService feignService;

    @RequestMapping("/order1")
    public String addOrder1(String name,int id) {
        // 调用用户，查询用户信息，
        String result = orderService.getUser(id);

        return "商品:"+name+",生成订单：" + result;
        }

    @ApiOperation(value = "查询详细订单情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "商品名称",dataType = "String",required = true),
            @ApiImplicitParam(name= "id",value = "用户编号",dataType = "String",required = true)
    })
    @RequestMapping(value = "/order",method = RequestMethod.GET)
    public String addOrder(String name,int id) {
        // 调用用户，查询用户信息，
        String result = feignService.getUser(id);

        return "商品:"+name+",生成订单：" + result;
    }


    @RequestMapping("/pool")
    public String pool() throws ExecutionException, InterruptedException {



        return orderService.testPool();
    }

    @RequestMapping(value = "/cache")
    public String cache () {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        // 调用用户,查询用户信息

        String result1 = orderService.getUser2(1, 123L);

        String result2 = orderService.getUser2(2, 123L);

        return "result1    "+result1+"result2     "+result2;

    }


    @RequestMapping(value = "/findTest")
    public String findTest() throws ExecutionException, InterruptedException {
        HystrixRequestContext context =HystrixRequestContext.initializeContext();

        Future<String> f1 = orderService.findOne(1);

        Future<String> f2 = orderService.findOne(2);

        Thread.sleep(500);

        Future<String> f3 = orderService.findOne(3);

        String res = f1.get()+" , "+f2.get() + " , "+f3.get();
        context.close();

        return res;
    }


}
