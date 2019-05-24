package com.apigeteway.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class MyFilterRouting extends ZuulFilter {
    //四种类型：pre,routing,error,post
    //pre：主要用在路由映射的阶段是寻找路由映射表的 MyFilterPre
    //routing:具体的路由转发过滤器是在routing路由器，具体的请求转发的时候会调用 MyFilterRouting
    //error:一旦前面的过滤器出错了，会调用error过滤器。MyFilterError
    //post:当routing，error运行完后才会调用该过滤器，是在最后阶段的 MyFilterPost
    @Override
    public String filterType() {
        System.out.println("==============执行了routing===============");
        return "routing";
    }

    //自定义过滤器执行的顺序，数值越大越靠后执行，越小就越先执行
    @Override
    public int filterOrder() {
        return 0;
    }

    //控制过滤器生效不生效，可以在里面写一串逻辑来控制
    //true放过 false拦截
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String routing = request.getParameter("routing");
        if(StringUtils.isNotEmpty(routing)){
            //如果携带routing参数则放行！
            return true;
        }
        return false;
    }

    //执行过滤逻辑
    @Override
    public Object run() throws ZuulException {

        RequestContext  currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String routing = request.getParameter("routing");
        System.out.println("==>routing:"+routing);
        return routing;
    }
}
