package com.bs.demo.service;

import com.bs.demo.common.Result;
import com.bs.demo.entity.DepTask;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author gf
 * @since 2021-12-28
 */
public interface IDepTaskService extends IService<DepTask> {

    Result addDepTask(DepTask depTask);

    Result updateDepTask(DepTask depTask);

    Result deleteDepTask(List<DepTask> depTaskList);
}
