package com.money.more.data.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.money.more.data.entity.UserRoleInfo;
import com.money.more.data.entity.UserSaltInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSaltInfoMapper extends BaseMapper<UserSaltInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(UserSaltInfo record);

    int insertSelective(UserSaltInfo record);

    UserSaltInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserSaltInfo record);

    int updateByPrimaryKey(UserSaltInfo record);
}