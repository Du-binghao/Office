package com.bs.demo.service;

import com.bs.demo.common.Result;
import com.bs.demo.entity.Dep;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author gf
 * @since 2021-12-28
 */
public interface IDepService extends IService<Dep> {

    Result addDep(Dep dep);

    Result updateDep(Dep dep);

    Result deleteDep(List<Dep> depList);
}
