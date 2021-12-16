package com.money.more.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.money.more.data.entity.BaseDict;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseDictMapper extends BaseMapper<BaseDict> {
    int deleteByPrimaryKey(Long id);

    int insert(BaseDict record);

    int insertSelective(BaseDict record);

    BaseDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseDict record);

    int updateByPrimaryKey(BaseDict record);
}