package com.hxr.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: FeignService
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-11 16:39
 * @Version: 1.0
 **/
@FeignClient(value = "hxr-user",fallback = MyFallback.class)
public interface FeignService {

    @RequestMapping(value ="/user/{id}",method = RequestMethod.GET)
    String getUser(@PathVariable("id") int id);



}
