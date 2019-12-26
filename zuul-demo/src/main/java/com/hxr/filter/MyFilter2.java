package com.hxr.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @ClassName: MyFilter1
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-26 9:19
 * @Version: 1.0
 **/
//@Component
public class MyFilter2 extends ZuulFilter {

    /**
     * 类型包括 pre post route error
     * pre 代表在路由代理之前执行
     * post 代表在route 或者是 error 执行完成后执行
     * route 代表代理的时候执行
     * error 代表出现错的时候执行
     */

    @Override
    public String filterType() {
        // 路由之前(后置过滤器)
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        // 优先级,数字越大,优先级越低
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        // 是否执行该过滤器,true代表需要过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("MyFilter2 run");
        return null;
    }
}
