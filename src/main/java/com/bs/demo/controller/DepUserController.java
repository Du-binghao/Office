package com.bs.demo.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;

import com.bs.demo.dto.LoginUserDto;
import com.bs.demo.entity.Dep;
import com.bs.demo.entity.User;
import com.bs.demo.service.IDepService;
import com.bs.demo.service.IDepUserService;
import com.bs.demo.entity.DepUser;


import com.bs.demo.service.IUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import com.bs.demo.annotation.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gf
 * @since 2022-05-26
 */
@Controller
@RequestMapping("/depUser")
@Api(tags = "")
@RestController
public class DepUserController {

    @Autowired
    public IDepUserService iDepUserService;

    @Autowired
    public IUserService iUserService;

    @Autowired
    public IDepService iDepService;

    @GetMapping("/dep_user")
    public Result getDepUser(Integer current){
        LoginUserDto loginUserDto = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<User> page = new Page<>(current, 10);
        QueryWrapper<Dep> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dep_manager",loginUserDto.getUser().getUserId());
        Integer depId = iDepService.getOne(queryWrapper).getDepId();
        List<Object> userids = iDepUserService.list(new QueryWrapper<DepUser>()
                .eq("dep_id", depId)).stream().map(DepUser::getUserId).collect(Collectors.toList());
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("user_id",userids);
        Page<User> result = iUserService.page(page, queryWrapper1);
        return Result.success().code(ResultCode.SUCCESS).message("查询成功").data(result);
    }


    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "列表")
    public Result depUserList(@RequestBody JSONObject jsonObject) {
        Integer current = jsonObject.get("current", Integer.class);
        Integer depId = jsonObject.get("depId", Integer.class);
        String searchText = jsonObject.get("searchText", String.class);
        List<Object> users = iDepUserService.list(new QueryWrapper<DepUser>()
                .eq("dep_id", depId)).stream().map(DepUser::getUserId).collect(Collectors.toList());
        Page<User> page = new Page<>(current, 10);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (users.size() != 0){
            queryWrapper.in("user_id", users);
            queryWrapper.like("nick_name", searchText);
            Page<User> result = iUserService.page(page, queryWrapper);
            return Result.success().code(ResultCode.SUCCESS).message("查询成功").data(result);

        } else {
            return Result.success().code(ResultCode.SUCCESS).message("查询成功").data(page);

        }
    }

    @PostMapping("/add")
    @ResponseBody
    @Transactional
    public Result depUserAdd(@RequestBody JSONObject jsonObject) {
        User user = jsonObject.get("user",User.class);
        Integer depId = jsonObject.get("depId",Integer.class);
        Result result = iDepUserService.addDepUser(user, depId);
        return result;
    }


    @OperationLog("修改")
    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "修改")
    @PreAuthorize("hasAnyAuthority('depUser:edit')")
    @Transactional
    public Result depUserEdit(@RequestBody @Valid DepUser depUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error().message(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Result result = iDepUserService.updateDepUser(depUser);
        return result;
    }


    @PostMapping("/del")
    @ResponseBody
    public Result deleteDepUser(@RequestBody List<User> UserList) {
        Result result = iDepUserService.deleteDepUser(UserList);
        return result;
    }
}
