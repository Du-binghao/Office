package com.bs.demo.service;

import com.bs.demo.common.Result;
import com.bs.demo.entity.Finance;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author gf
 * @since 2021-12-28
 */
public interface IFinanceService extends IService<Finance> {

    Result addFinance(Finance finance);

    Result updateFinance(Finance finance);

    Result deleteFinance(List<Finance> financeList);
}
