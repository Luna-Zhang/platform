package com.money.more.data.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.money.more.data.entity.RoleInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);
}