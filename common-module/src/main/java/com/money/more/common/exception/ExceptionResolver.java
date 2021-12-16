package com.money.more.common.exception;

import com.money.more.common.utils.HttpResponse;
import com.money.more.common.utils.I18nUtil;
import com.money.more.common.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Describe：统一异常处理
 * @Author：luna
 * @Date：2021/12/2:17:35
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {
    private static Logger log = LoggerFactory.getLogger(ExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        HttpResponse result = new HttpResponse();
        String json = "";
        try {
            if (ex instanceof GlobalException) {
                resolverFrostException(ex, result);
                json = JsonUtils.toJSONString(result);
            } else {
                resolverOtherException(ex, result);
                json = JsonUtils.toJSONString(result);
            }
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache, must-revalidate");

            response.getWriter().write(json);
        } catch (Exception e) {
            log.error("与客户端通讯异常：{}",ex);
        }
        log.error("异常：" + ex.getMessage(), ex);

        return new ModelAndView();
    }

    /*
     * 处理业务层异常
     */
    private void resolverFrostException(Exception ex, HttpResponse result) {
        GlobalException frostException = (GlobalException) ex;
        result.setCode(frostException.getCode());
        String msg = I18nUtil.getString(result.getCode());
        if (StringUtils.isBlank(msg)) {
            result.setMsg(frostException.getMsg());
        } else {
            result.setMsg(msg);
        }
    }

    /*
     * 其他异常
     */
    private void resolverOtherException(Exception ex, HttpResponse result) {
        result.setCode("error_500");
        String msg = I18nUtil.getString(result.getCode());
        result.setMsg(msg + ex.getMessage());
    }
}
