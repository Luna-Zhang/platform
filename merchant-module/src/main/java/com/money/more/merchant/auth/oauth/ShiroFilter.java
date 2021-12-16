package com.money.more.merchant.auth.oauth;

import com.alibaba.fastjson.JSONObject;
import com.money.more.common.utils.HttpResponse;
import com.money.more.common.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Oauth2过滤器
 * @author LiJiaNan
 * @date Sep 1, 2018
 */
public class ShiroFilter extends FormAuthenticationFilter {

    private static Logger logger = LoggerFactory.getLogger(ShiroFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if(isLoginRequest(request,response)){
            if(isLoginSubmission(request,response)){
                if(logger.isTraceEnabled()){
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request,response);
            }else{
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }
                return true;
            }
        }else{
            HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse resp = (HttpServletResponse) response;
            if(req.getMethod().equals(RequestMethod.OPTIONS.name())) {
                resp.setStatus(org.springframework.http.HttpStatus.OK.value());
                return true;
            }

            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            String token = getRequestToken((HttpServletRequest) request);
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", getOrigin());
            if(StringUtils.isBlank(token)){
                HttpResponse error=HttpResponse.error(org.apache.http.HttpStatus.SC_UNAUTHORIZED+"","Token无效，请重新登录");

                httpResponse.getWriter().print(JsonUtils.toJSONString(error));
                return false;
            }else{
                this.saveRequest(request);
                HttpResponse error=HttpResponse.error(org.apache.http.HttpStatus.SC_UNAUTHORIZED+"","您的登陆已失效，请重新登陆");
                httpResponse.getWriter().print(JsonUtils.toJSONString(error));
            }

        }
        return false;
    }

    public static String getOrigin(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("Origin");
    }


    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json; charset=utf-8");
        try {
            // 处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            HttpResponse result = HttpResponse.error(HttpStatus.SC_UNAUTHORIZED+"", throwable.getMessage());
            String json = JSONObject.toJSONString(result);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {
        }
        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest){
        // 从header中获取token
        String token = httpRequest.getHeader("token");
        // 如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = httpRequest.getParameter("token");
        }
        return token;
    }

}
