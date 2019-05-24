package com.apigeteway.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class MyFilterPre extends ZuulFilter {

    //四种类型：pre,routing,error,post
    //pre：主要用在路由映射的阶段是寻找路由映射表的 MyFilterPre
    //routing:具体的路由转发过滤器是在routing路由器，具体的请求转发的时候会调用 MyFilterRouting
    //error:一旦前面的过滤器出错了，会调用error过滤器。MyFilterError
    //post:当routing，error运行完后才会调用该过滤器，是在最后阶段的 MyFilterPost
    @Override
    public String filterType() {
        //第1步 确认类型 执行顺序 pre=>routing=>error=>post 面对切面的思想
        System.out.println("==============执行了pre===============");
        return "pre";
    }

    //自定义过滤器执行的顺序，数值越大越靠后执行，越小就越先执行
    @Override
    public int filterOrder() {
        //第2步 同一类中 存在多个过滤器的执行顺序 数值越大越靠后执行
        return 0;
    }

    //控制过滤器生效不生效，可以在里面写一串逻辑来控制
    //true放过 false拦截
    @Override
    public boolean shouldFilter() {
        //第3步 控制是否执行该过滤器
        return true;
    }

    //执行过滤逻辑
    @Override
    public Object run() throws ZuulException {

        //第4步 业务处理 权限校验(必须携带token值)
        RequestContext  context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getParameter("token");
        if (token == null){
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.setResponseBody("unAuthrized");
        }
        return null;
    }
  // 控制台输出
//            ==============执行了pre===============
//            ==============执行了routing===============
//            ==============执行了error===============
//            ==============执行了post===============
}
