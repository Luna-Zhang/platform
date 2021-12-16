package com.money.more.data.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.money.more.data.entity.RolePermissionInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionInfoMapper extends BaseMapper<RolePermissionInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermissionInfo record);

    int insertSelective(RolePermissionInfo record);

    RolePermissionInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermissionInfo record);

    int updateByPrimaryKey(RolePermissionInfo record);
}