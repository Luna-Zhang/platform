package com.money.more.merchant.controller;

import com.money.more.common.utils.HttpResponse;
import com.money.more.data.entity.TaskInfo;
import com.money.more.data.model.TaskBean;
import com.money.more.data.service.TaskInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 任务表 前端控制器
 * </p>
 *
 * @author luna
 * @since 2021-11-24
 */
@RestController
@RequestMapping("/taskInfo")
@Api(tags = "任务表")
public class TaskInfoController {

    @Resource
    private TaskInfoService service;

    @ApiOperation("发布任务")
    @PostMapping(value = "/createTask")
    public HttpResponse createTask(@RequestBody TaskBean entity) {
        // service.add(entity);
        return HttpResponse.ok();
    }

    @ApiOperation("通过主键删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public HttpResponse batchDelete(@PathVariable("id") Integer id) {
        service.deleteById(id);
        return HttpResponse.ok();
    }

    @ApiOperation("编辑")
    @PutMapping(value = "/updateById")
    public HttpResponse edit(@RequestBody TaskInfo entity) {
        service.edit(entity);
        return HttpResponse.ok();
    }

    @ApiOperation("根据id获取信息")
    @GetMapping(value = "/getOneInfoById/{id}")
    public HttpResponse getInfoById(@PathVariable("id") Integer id) {
        return HttpResponse.ok(service.getOneBaseDictByParams(id));
    }

    @ApiOperation("分页查询")
    @GetMapping(value = "/getPageList")
    public HttpResponse get(@RequestBody TaskInfo entity) {
        return HttpResponse.ok(service.getBaseDictListPage(entity));
    }
}

