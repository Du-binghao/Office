package com.bs.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;

import com.bs.demo.service.IFinanceService;
import com.bs.demo.entity.Finance;
import com.bs.demo.mapper.FinanceMapper;

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
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance> implements IFinanceService {

    @Autowired
    private FinanceMapper financeMapper;


    @Override
    public Result addFinance(Finance finance) {
        finance.setCreateTime(LocalDateTime.now());
        financeMapper.insert(finance);
        return Result.success().data("添加成功！");
    }

    @Override
    public Result updateFinance(Finance finance) {
        return Result.success().message("修改成功");
    }

    @Override
    public Result deleteFinance(List<Finance> financeList) {
        List<Integer> ids = financeList.stream().map(Finance::getFinanceId).collect(Collectors.toList());
        financeMapper.deleteBatchIds(ids);
        return Result.success().message("删除成功！");
    }
}