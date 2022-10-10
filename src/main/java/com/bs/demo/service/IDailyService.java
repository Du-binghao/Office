package com.bs.demo.service;

import com.bs.demo.common.Result;
import com.bs.demo.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author gf
 * @since 2021-12-28
 */
public interface IDailyService extends IService<Daily> {

    Result addDaily(Daily daily);

    Result updateDaily(Daily daily);

    Result deleteDaily(List<Daily> dailyList);
}
