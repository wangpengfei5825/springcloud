package com.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//自动注入spring
@Component
public class LoginFilter extends ZuulFilter {
    //选择拦截的时机
    @Override
    public String filterType() {
        //后置拦截
//        FilterConstants.POST_TYPE;
        //路由拦截
//        FilterConstants.ROUTE_TYPE;
        //错误拦截
//        FilterConstants.ERROR_TYPE;
        //前置拦截
        return FilterConstants.PRE_TYPE;
    }
    //优先级
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;
    }

    //是否拦截
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //拦截逻辑
    @Override
    public Object run() throws ZuulException {
        //获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取request
        HttpServletRequest request = currentContext.getRequest();

        String token = request.getParameter("access-token");
        //判断是否为空
        if (StringUtils.isBlank(token)){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null;
    }
}
