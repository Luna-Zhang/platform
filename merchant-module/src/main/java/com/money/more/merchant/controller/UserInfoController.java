package com.money.more.merchant.controller;

import com.money.more.common.exception.GlobalException;
import com.money.more.common.utils.HttpResponse;
import com.money.more.common.utils.MobileUtil;
import com.money.more.data.entity.UserInfo;
import com.money.more.data.model.UserBean;
import com.money.more.data.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author luna
 * @since 2021-11-24
 */
@Api(tags = "用户信息")
@RestController
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;


    @ApiOperation("忘记密码")
    @PostMapping(value = "/forgetPassword")
    public HttpResponse forgetPassword(@RequestBody UserBean userBean) {
        if (StringUtils.isBlank(userBean.getMobile()) || StringUtils.isBlank(userBean.getVerifyCode())
        || StringUtils.isBlank(userBean.getPassword()) || StringUtils.isBlank(userBean.getPasswordAgain())) {
            throw new GlobalException("error_10001");
        }
        // 校验手机号格式
        if (!MobileUtil.checkMobile(userBean.getMobile())) {
            throw new GlobalException("error_10005");
        }
        userInfoService.forgetPassword(userBean);
        return HttpResponse.ok();
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/updatePassword")
    public HttpResponse updatePassword (@RequestBody UserBean userBean) {
        if (StringUtils.isBlank(userBean.getPassword()) || StringUtils.isBlank(userBean.getPasswordAgain())) {
            throw new GlobalException("error_10001");
        }
        userInfoService.updatePassword(userBean);
        return HttpResponse.ok();
    }

    @ApiOperation("通过主键删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public HttpResponse batchDelete(@PathVariable("id") Integer id) {
        userInfoService.deleteById(id);
        return HttpResponse.ok();
    }

    @ApiOperation("编辑")
    @PutMapping(value = "/updateById")
    public HttpResponse edit(@RequestBody UserInfo entity) {
        userInfoService.edit(entity);
        return HttpResponse.ok();
    }

    @ApiOperation("根据id获取信息")
    @GetMapping(value = "/getOneInfoById/{id}")
    public HttpResponse getInfoById(@PathVariable("id") Integer id) {
        return HttpResponse.ok(userInfoService.getOneBaseDictByParams(id));
    }

    @ApiOperation("分页查询")
    @GetMapping(value = "/getPageList")
    public HttpResponse get(@RequestBody UserInfo entity) {
        return HttpResponse.ok(userInfoService.getBaseDictListPage(entity));
    }
}

