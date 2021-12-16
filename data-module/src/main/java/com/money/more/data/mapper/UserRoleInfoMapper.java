package com.money.more.data.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.money.more.data.entity.UserRoleInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleInfoMapper extends BaseMapper<UserRoleInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(UserRoleInfo record);

    int insertSelective(UserRoleInfo record);

    UserRoleInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRoleInfo record);

    int updateByPrimaryKey(UserRoleInfo record);
}