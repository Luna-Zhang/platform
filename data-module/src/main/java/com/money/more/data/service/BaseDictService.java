package com.money.more.data.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.money.more.common.contants.DbFieldConstant;
import com.money.more.common.enums.DictLabelEnum;
import com.money.more.common.enums.StatusEnum;
import com.money.more.data.entity.BaseDict;
import com.money.more.data.mapper.BaseDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 基础字典表 服务实现类
 * </p>
 *
 * @author luna
 * @since 2021-11-24
 */
@Service
public class BaseDictService extends ServiceImpl<BaseDictMapper, BaseDict> {

    @Autowired
    private BaseDictMapper baseDictMapper;

    /**
     * 获取平台类型
     * @return
     */
    public Map<String, Object> getPlatformTypeList() {
        QueryWrapper<BaseDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DbFieldConstant.LABEL, DictLabelEnum.PLATFORM_TYPE.getLabel());
        queryWrapper.eq(DbFieldConstant.STATUS, StatusEnum.ENABLE.getStatus());
        List<BaseDict> baseDictList = baseDictMapper.selectList(queryWrapper);

        return baseDictList.stream().collect(Collectors.toMap(BaseDict::getDictCode, BaseDict::getDictValue));
    }

    /**
     * 获取业务类型
     * @param platformType
     * @return
     */
    public Map<String, Object> getBusinessType (String platformType) {
        QueryWrapper<BaseDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DbFieldConstant.LABEL, DictLabelEnum.BUSINESS_TYPE.getLabel());
        queryWrapper.eq(DbFieldConstant.PARENT_CODE, platformType);
        queryWrapper.eq(DbFieldConstant.STATUS, StatusEnum.ENABLE.getStatus());
        List<BaseDict> baseDictList = baseDictMapper.selectList(queryWrapper);

        return baseDictList.stream().collect(Collectors.toMap(BaseDict::getDictCode, BaseDict::getDictValue));
    }

    /**
     * 获取商户价格
     * @param businessType
     * @return
     */
    public Map<String, Object> getMerchantPrice (String businessType) {
        QueryWrapper<BaseDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DbFieldConstant.LABEL, DictLabelEnum.MERCHANT_PRICE_TYPE.getLabel());
        queryWrapper.eq(DbFieldConstant.PARENT_CODE, businessType);
        queryWrapper.eq(DbFieldConstant.STATUS, StatusEnum.ENABLE.getStatus());
        List<BaseDict> baseDictList = baseDictMapper.selectList(queryWrapper);

        return baseDictList.stream().collect(Collectors.toMap(BaseDict::getDictCode, BaseDict::getDictValue));
    }

    /**
     * 获取兼职价格
     * @param businessType
     * @return
     */
    public Map<String, Object> getUserPrice (String businessType) {
        QueryWrapper<BaseDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DbFieldConstant.LABEL, DictLabelEnum.USER_PRICE_TYPE.getLabel());
        queryWrapper.eq(DbFieldConstant.PARENT_CODE, businessType);
        queryWrapper.eq(DbFieldConstant.STATUS, StatusEnum.ENABLE.getStatus());
        List<BaseDict> baseDictList = baseDictMapper.selectList(queryWrapper);

        return baseDictList.stream().collect(Collectors.toMap(BaseDict::getDictCode, BaseDict::getDictValue));
    }



    /**
     * 新增
     *
     * @param entity 实体
     * @return int
     */
    public void add(BaseDict entity) {
        int insert = baseDictMapper.insert(entity);
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
        int only = baseDictMapper.deleteByPrimaryKey(Long.valueOf(id));
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
    public void edit(BaseDict entity) {
        int i = baseDictMapper.updateByPrimaryKey(entity);
        if (i <= 0) {
            // TODO 异常
        }
    }

    /**
     * 通过查询参数获取单个BaseDict对象
     *
     * @param id id
     * @return BaseDictItem
     */
    public BaseDict getOneBaseDictByParams(Integer id) {
        return baseDictMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    /**
     * 分页查询
     *
     * @param entity
     * @return IPage<BaseDictItem>
     * TODO 分页查询的当前页和条数需要修改
     */
    public IPage<BaseDict> getBaseDictListPage(BaseDict entity) {
        IPage<BaseDict> page = new Page<>(1, 10);
        QueryWrapper<BaseDict> param = new QueryWrapper<>();
        return baseDictMapper.selectPage(page, param);
    }

}