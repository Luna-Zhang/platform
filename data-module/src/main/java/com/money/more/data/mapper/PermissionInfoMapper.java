package com.money.more.data.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.money.more.data.entity.PermissionInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionInfoMapper extends BaseMapper<PermissionInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(PermissionInfo record);

    int insertSelective(PermissionInfo record);

    PermissionInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PermissionInfo record);

    int updateByPrimaryKey(PermissionInfo record);
}