package com.money.more.data.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.money.more.data.entity.MerchantFlowInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantFlowInfoMapper extends BaseMapper<MerchantFlowInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantFlowInfo record);

    int insertSelective(MerchantFlowInfo record);

    MerchantFlowInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantFlowInfo record);

    int updateByPrimaryKey(MerchantFlowInfo record);
}