package com.money.more.data.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.money.more.data.entity.RolePermissionInfo;
import com.money.more.data.mapper.RolePermissionInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author luna
 * @since 2021-11-24
 */
@Service
public class RolePermissionInfoService extends ServiceImpl<RolePermissionInfoMapper, RolePermissionInfo> {

    @Autowired
    private RolePermissionInfoMapper entityMapper;

    /**
     * 新增
     *
     * @param entity 实体
     * @return int
     */
    public void add(RolePermissionInfo entity) {
        int insert = entityMapper.insert(entity);
        if (insert <= 0) {
            // TODO 异常
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @return int
     */
    public void deleteById(Integer id) {
        int only = entityMapper.deleteByPrimaryKey(Long.valueOf(id));
        if (only <= 0) {
            // TODO 异常
        }
    }

    /**
     * 根据id修改
     *
     * @param entity
     * @return int
     */
    public void edit(RolePermissionInfo entity) {
        int i = entityMapper.updateByPrimaryKey(entity);
        if (i <= 0) {
            // TODO 异常
        }
    }

    /**
     * 通过查询参数获取单个BaseDict对象
     *
     * @param id id
     * @return RolePermissionInfo
     */
    public RolePermissionInfo getOneBaseDictByParams(Integer id) {
        return entityMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    /**
     * 分页查询
     *
     * @param entity
     * @return IPage<RolePermissionInfo>
     * TODO 分页查询的当前页和条数需要修改
     */
    public IPage<RolePermissionInfo> getBaseDictListPage(RolePermissionInfo entity) {
        IPage<RolePermissionInfo> page = new Page<>(1, 10);
        QueryWrapper<RolePermissionInfo> param = new QueryWrapper<>();
        return entityMapper.selectPage(page, param);
    }

}