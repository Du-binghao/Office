package com.bs.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;

import com.bs.demo.service.IDepService;
import com.bs.demo.entity.Dep;
import com.bs.demo.mapper.DepMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author gf
 * @since 2021-12-28
 */
@Service
public class DepServiceImpl extends ServiceImpl<DepMapper, Dep> implements IDepService {

    @Autowired
    private DepMapper depMapper;


    @Override
    public Result addDep(Dep dep) {
        return Result.success().message("添加成功！");
    }

    @Override
    public Result updateDep(Dep dep) {
        return Result.success().message("修改成功");
    }

    @Override
    public Result deleteDep(List<Dep> depList) {
        List<Integer> ids = depList.stream().map(Dep::getDepId).collect(Collectors.toList());
        depMapper.deleteBatchIds(ids);
        return Result.success().message("删除成功！");
    }
}