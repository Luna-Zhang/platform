package com.money.more.data.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.money.more.data.entity.TaskDetailInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDetailInfoMapper extends BaseMapper<TaskDetailInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(TaskDetailInfo record);

    int insertSelective(TaskDetailInfo record);

    TaskDetailInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskDetailInfo record);

    int updateByPrimaryKey(TaskDetailInfo record);
}