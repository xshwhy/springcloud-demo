package com.hxr.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName: MyFilter1
 * @Description:
 * @Author: xsh
 * @Date: 2019-12-26 9:19
 * @Version: 1.0
 **/
//@Component
public class MyFilter1 extends ZuulFilter {

    /**
     * 类型包括 pre post route error
     * pre 代表在路由代理之前执行
     * post 代表在route 或者是 error 执行完成后执行
     * route 代表代理的时候执行
     * error 代表出现错的时候执行
     */

    @Override
    public String filterType() {
        // 路由之前(前置过滤器)
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 优先级,数字越大,优先级越低
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        // 是否执行该过滤器,true代表需要过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("MyFilter1 run");
        // 判断
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            try {
                context.getResponse().getWriter().print("token error");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }
}
