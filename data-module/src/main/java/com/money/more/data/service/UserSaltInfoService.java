package com.money.more.data.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.money.more.common.contants.DbFieldConstant;
import com.money.more.data.entity.UserSaltInfo;
import com.money.more.data.mapper.UserSaltInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author luna
 * @since 2021-11-24
 */
@Service
public class UserSaltInfoService extends ServiceImpl<UserSaltInfoMapper, UserSaltInfo> {

    @Autowired
    private UserSaltInfoMapper entityMapper;

    /**
     * 获取用户盐
     * @param userId
     * @return
     */
    public String getSalt (String userId) {
        QueryWrapper query = new QueryWrapper();
        query.eq(DbFieldConstant.USER_ID, userId);
        List<UserSaltInfo> list = entityMapper.selectList(query);
        return CollectionUtils.isEmpty(list) ? null : list.get(0).getSalt();
    }

    /**
     * 用户唯一盐值保存
     *
     * @param userId 用户id
     * @param salt   盐
     */
    public void addUserSaltInfo(String userId, String salt) {
        UserSaltInfo userSaltInfo = new UserSaltInfo();
        userSaltInfo.setUserId(userId);
        userSaltInfo.setSalt(salt);
        userSaltInfo.setCreateBy("system");
        userSaltInfo.setLastUpdateBy("system");

        entityMapper.insertSelective(userSaltInfo);
    }

}