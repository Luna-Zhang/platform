package com.money.more.merchant.controller;

import com.money.more.common.exception.GlobalException;
import com.money.more.common.utils.HttpResponse;
import com.money.more.common.utils.MobileUtil;
import com.money.more.data.model.UserBean;
import com.money.more.data.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describe：登录模块
 * @Author：luna
 * @Date：2021/11/29:15:49
 */
@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    public HttpResponse register (@RequestBody UserBean bean) {
        if (StringUtils.isBlank(bean.getMobile()) || StringUtils.isBlank(bean.getPassword())
                || StringUtils.isBlank(bean.getPasswordAgain()) || StringUtils.isBlank(bean.getVerifyCode())) {
            throw new GlobalException("error_10001");
        }
        // 校验手机号格式
        if (!MobileUtil.checkMobile(bean.getMobile())) {
            throw new GlobalException("error_10005");
        }
        userInfoService.register(bean);
        return HttpResponse.ok();
    }

    @ApiOperation("登录接口")
    @PostMapping(value = "/login")
    public HttpResponse loginUser(@RequestBody UserBean bean) {
        if (StringUtils.isBlank(bean.getMobile()) || StringUtils.isBlank(bean.getPassword())) {
           throw new GlobalException("error_10001");
        }
        // 校验手机号格式
        if (!MobileUtil.checkMobile(bean.getMobile())) {
            throw new GlobalException("error_10005");
        }

        return HttpResponse.ok(userInfoService.login(bean));
    }

    @ApiOperation("获取验证码")
    @GetMapping(value = "/getVerifyCode")
    public HttpResponse getVerifyCode(@Param("mobile") String mobile) {
        // 校验手机号格式
        if (!MobileUtil.checkMobile(mobile)) {
            throw new GlobalException("error_10005");
        }
        userInfoService.getVerifyCode(mobile);
        return HttpResponse.ok();
    }


}
