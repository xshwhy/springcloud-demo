package com.hxr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserController
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-04 17:07
 * @Version: 1.0
 **/
@Api(value = "用户接口")
@RestController
public class UserController {

    @Value("${server.port}")
    int port;

    @ApiOperation(value = "查询id对应数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "用户编号",dataType = "String",required = true)
    })
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String getUser(@PathVariable("id") Integer id) {

        if(id == 1) {
            return "大佬" + port;
        } else if (id == 2) {
            return "超人" + port;
        } else {
            return "hxr" + port;
        }

    }


    @RequestMapping("/getUserAll")
    public List<String> getUser(@RequestParam List<Integer> ids) {
        ArrayList<String> list = new ArrayList<>();
        for (Integer id : ids) {
            list.add("用户:" + id);
        }
        return list;

    }



}
