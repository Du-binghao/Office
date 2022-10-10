package com.bs.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;

import com.bs.demo.service.IDailyService;
import com.bs.demo.entity.Daily;
import com.bs.demo.mapper.DailyMapper;

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
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements IDailyService {

    @Autowired
    private DailyMapper dailyMapper;


    @Override
    public Result addDaily(Daily daily) {
        return Result.success().message("添加成功！");
    }

    @Override
    public Result updateDaily(Daily daily) {
        return Result.success().message("修改成功");
    }

    @Override
    public Result deleteDaily(List<Daily> dailyList) {
        List<Integer> ids = dailyList.stream().map(Daily::getDailyId).collect(Collectors.toList());
        dailyMapper.deleteBatchIds(ids);
        return Result.success().message("删除成功！");
    }
}