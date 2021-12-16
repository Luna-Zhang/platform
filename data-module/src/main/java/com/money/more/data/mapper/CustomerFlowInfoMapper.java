package com.money.more.data.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.money.more.data.entity.CustomerFlowInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerFlowInfoMapper extends BaseMapper<CustomerFlowInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerFlowInfo record);

    int insertSelective(CustomerFlowInfo record);

    CustomerFlowInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerFlowInfo record);

    int updateByPrimaryKey(CustomerFlowInfo record);
}