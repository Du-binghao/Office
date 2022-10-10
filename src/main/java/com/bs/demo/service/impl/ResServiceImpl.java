package com.bs.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;

import com.bs.demo.service.IResService;
import com.bs.demo.entity.Res;
import com.bs.demo.mapper.ResMapper;

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
public class ResServiceImpl extends ServiceImpl<ResMapper, Res> implements IResService {

    @Autowired
    private ResMapper resMapper;


    @Override
    public Result addRes(Res res) {
        return Result.success().message("添加成功！");
    }

    @Override
    public Result updateRes(Res res) {
        return Result.success().message("修改成功");
    }

    @Override
    public Result deleteRes(List<Res> resList) {
        List<Integer> ids = resList.stream().map(Res::getResId).collect(Collectors.toList());
        resMapper.deleteBatchIds(ids);
        return Result.success().message("删除成功！");
    }
}