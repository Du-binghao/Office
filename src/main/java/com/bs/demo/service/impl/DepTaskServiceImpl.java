package com.bs.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;

import com.bs.demo.service.IDepTaskService;
import com.bs.demo.entity.DepTask;
import com.bs.demo.mapper.DepTaskMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author gf
 * @since 2021-12-28
 */
@Service
public class DepTaskServiceImpl extends ServiceImpl<DepTaskMapper, DepTask> implements IDepTaskService {

    @Autowired
    private DepTaskMapper depTaskMapper;


    @Override
    public Result addDepTask(DepTask depTask) {
        depTask.setCreateTime(LocalDateTime.now());
        depTaskMapper.insert(depTask);
        return Result.success().message("添加成功！");
    }

    @Override
    public Result updateDepTask(DepTask depTask) {
        return Result.success().message("修改成功");
    }

    @Override
    public Result deleteDepTask(List<DepTask> depTaskList) {
        List<Integer> ids = depTaskList.stream().map(DepTask::getTaskId).collect(Collectors.toList());
        depTaskMapper.deleteBatchIds(ids);
        return Result.success().message("删除成功！");
    }
}