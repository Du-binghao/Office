package com.bs.demo.service;

import com.bs.demo.common.Result;
import com.bs.demo.entity.Res;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author gf
 * @since 2021-12-28
 */
public interface IResService extends IService<Res> {

    Result addRes(Res res);

    Result updateRes(Res res);

    Result deleteRes(List<Res> resList);
}
