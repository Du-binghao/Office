package com.bs.demo.service;

import com.bs.demo.common.Result;
import com.bs.demo.entity.DepUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.demo.entity.User;

import java.util.List;

/**
 *
 * @author gf
 * @since 2021-12-28
 */
public interface IDepUserService extends IService<DepUser> {

    Result addDepUser(User userInfo,Integer depId);

    Result updateDepUser(DepUser depUser);

    Result deleteDepUser(List<User> depUserList);
}
