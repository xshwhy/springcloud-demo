package com.hxr.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName: MyFallback
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-19 19:03
 * @Version: 1.0
 **/
@Component
public class MyFallback implements FeignService {

    @Override
    public String getUser(int id) {
        return "error getUsr";
    }
}
