package com.money.more.data.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.money.more.data.entity.TaskInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskInfoMapper extends BaseMapper<TaskInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(TaskInfo record);

    int insertSelective(TaskInfo record);

    TaskInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskInfo record);

    int updateByPrimaryKey(TaskInfo record);
}