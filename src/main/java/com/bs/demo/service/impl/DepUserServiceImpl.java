package com.bs.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.demo.common.Result;

import com.bs.demo.dto.LoginUserDto;
import com.bs.demo.entity.RoleUser;
import com.bs.demo.entity.User;
import com.bs.demo.mapper.RoleUserMapper;
import com.bs.demo.mapper.UserMapper;
import com.bs.demo.service.IDepUserService;
import com.bs.demo.entity.DepUser;
import com.bs.demo.mapper.DepUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author gf
 * @since 2021-12-28
 */
@Service
public class DepUserServiceImpl extends ServiceImpl<DepUserMapper, DepUser> implements IDepUserService {

    @Autowired
    private DepUserMapper depUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleUserMapper roleUserMapper;


    @Override
    public Result addDepUser(User userInfo, Integer depId) {
        LoginUserDto loginUserDto = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        User userCheck = userMapper.selectOne(queryWrapper.eq("user_name", userInfo.getUserName()));
        if (userCheck == null) {
            User user = new User();
            user.setUserName(userInfo.getUserName());
            user.setNickName(userInfo.getNickName());
            user.setPassword(bCryptPasswordEncoder.encode("123456"));
            user.setStatus(1);
            user.setEmail(userInfo.getEmail());
            user.setPhone(userInfo.getPhone());
            user.setCreateUser(loginUserDto.getUsername());
            user.setCreateTime(LocalDateTime.now());
            user.setAvatar("/assets/admin/avatar.gif");
            if (userMapper.insert(user) == 1) {
                User newUser = userMapper.selectOne(queryWrapper.eq("user_name", user.getUserName()));
                RoleUser roleUser = new RoleUser();
                roleUser.setRoleId(3);
                roleUser.setUserId(newUser.getUserId());
                DepUser depuser = new DepUser();
                depuser.setDepId(depId);
                depuser.setUserId(newUser.getUserId());
                depUserMapper.insert(depuser);
                if (roleUserMapper.insert(roleUser) == 1) {
                    return Result.success().message("用户添加成功！").data(newUser.getUserName());
                } else {
                    return Result.error().message("角色添加失败！").data(newUser.getUserName());
                }
            } else {
                return Result.error().message("添加失败！").data(user.getUserName());
            }
        } else {
            return Result.error().message("用户已存在！").data(userInfo.getUserName());
        }
    }

    @Override
    public Result updateDepUser(DepUser depUser) {
        return Result.success().message("修改成功");
    }

    @Override
    public Result deleteDepUser(List<User> depUserList) {
        List<Integer> ids = depUserList.stream().map(User::getUserId).collect(Collectors.toList());
        depUserMapper.delete(new QueryWrapper<DepUser>()
                .in("user_id",ids));
        return Result.success().message("删除成功！");
    }
}