package com.money.more.merchant.controller;


import com.money.more.common.utils.HttpResponse;
import com.money.more.data.entity.BaseDict;
import com.money.more.data.service.BaseDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 父级基础字典表 前端控制器
 * </p>
 *
 * @author luna
 * @since 2021-11-24
 */
@Api(tags = "父级基础字典表")
@RequestMapping(value = "baseDict")
@RestController
public class BaseDictController {

    @Resource
    private BaseDictService baseDictService;


    @ApiOperation("获取平台类型")
    @GetMapping(value = "/getPlatformType")
    public HttpResponse getPlatformType () {
        return HttpResponse.ok(baseDictService.getPlatformTypeList());
    }

    @ApiOperation("获取业务类型")
    @GetMapping(value = "/getBusinessType")
    public HttpResponse getBusinessType (@Param("platformType") String platformType) {
        return HttpResponse.ok(baseDictService.getBusinessType(platformType));
    }

    @ApiOperation("获取商户业务类型价格")
    @GetMapping(value = "/getMerchantPrice")
    public HttpResponse getMerchantPrice (@Param("businessType") String businessType) {
        return HttpResponse.ok(baseDictService.getMerchantPrice(businessType));
    }

    @ApiOperation("获取兼职业务类型价格")
    @GetMapping(value = "/getUserPrice")
    public HttpResponse getUserPrice (@Param("businessType") String businessType) {
        return HttpResponse.ok(baseDictService.getUserPrice(businessType));
    }

    @ApiOperation("添加")
    @PostMapping(value = "/add")
    public HttpResponse add(@RequestBody BaseDict entity) {
        baseDictService.add(entity);
        return HttpResponse.ok();
    }

    @ApiOperation("通过主键删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public HttpResponse batchDelete(@PathVariable("id") Integer id) {
        baseDictService.deleteById(id);
        return HttpResponse.ok();
    }

    @ApiOperation("编辑")
    @PutMapping(value = "/updateById")
    public HttpResponse edit(@RequestBody BaseDict entity) {
        baseDictService.edit(entity);
        return HttpResponse.ok();
    }

    @ApiOperation("根据id获取信息")
    @GetMapping(value = "/getOneInfoById/{id}")
    public HttpResponse getInfoById(@PathVariable("id") Integer id) {
        return HttpResponse.ok(baseDictService.getOneBaseDictByParams(id));
    }

//    @ApiOperation("分页查询")
//    @GetMapping(value = "/getPageList")
//    public HttpResponse get(@RequestBody BaseDict entity) {
//        return HttpResponse.ok(baseDictService.getBaseDictListPage(entity));
//    }
}

