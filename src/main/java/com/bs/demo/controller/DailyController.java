package com.bs.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;
import com.bs.demo.common.SearchOption;

import com.bs.demo.dto.LoginUserDto;
import com.bs.demo.entity.Dep;
import com.bs.demo.entity.DepUser;
import com.bs.demo.entity.User;
import com.bs.demo.service.IDailyService;
import com.bs.demo.entity.Daily;


import com.bs.demo.service.IDepService;
import com.bs.demo.service.IDepUserService;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author gf
 * @since 2022-05-26
 */
@Controller
@RestController
@RequestMapping("/daily")
@Api(tags = "")
public class DailyController {

    @Autowired
    public IDailyService iDailyService;

    @Autowired
    public IDepService iDepService;

    @Autowired
    public IUserService iUserService;

    @Autowired
    public IDepUserService iDepUserService;

    @GetMapping("/getUserCode")
    public Result getUserCode() {
        LoginUserDto loginUserDto = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Daily> codes = iDailyService.list(new QueryWrapper<Daily>()
                .eq("user_id", loginUserDto.getUser().getUserId()));
        if (codes.size() == 0) {
            return Result.success().data(0);
        } else {
            System.out.println();
            if (codes.get(codes.size() - 1).getCreateTime().equals(LocalDate.now())) {
                return Result.success().data(1);

            } else {
                return Result.success().data(0);
            }
        }
    }


    @GetMapping("/depUsers")
    public Result getDepUsers() {
        LoginUserDto loginUserDto = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<DepUser> userList = iDepUserService.list(new QueryWrapper<DepUser>()
                .eq("dep_id", iDepUserService.getOne(new QueryWrapper<DepUser>()
                        .eq("user_id", loginUserDto.getUser().getUserId())).getDepId()));

        userList.forEach(item -> {

            List<Daily> dailies = iDailyService.list(new QueryWrapper<Daily>()
                    .eq("user_id", item.getUserId()));

            item.setUserId(item.getUserId() + "||" + iUserService.getOne(new QueryWrapper<User>()
                    .eq("user_id", item.getUserId())).getNickName());


            if (dailies.size() == 0) {
                item.setDepId(0);
            } else {
                if (dailies.get(dailies.size() - 1).getCreateTime().equals(LocalDate.now())) {
                    item.setDepId(1);
                } else {
                    item.setDepId(0);
                }
            }
        });
        return Result.success().data(userList);
    }


    @GetMapping("/list")
    @ResponseBody
    public Result dailyList(Integer current) {
        LoginUserDto loginUserDto = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QueryWrapper<Dep> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dep_manager", loginUserDto.getUser().getUserId());
        Integer depId = iDepService.getOne(queryWrapper).getDepId();

        QueryWrapper<Daily> aaa = new QueryWrapper<Daily>();
        aaa.eq("dep_id", depId);
        aaa.orderByDesc("create_time");


        Page<Daily> page = new Page<>(current, 10);
        Page<Daily> result = iDailyService.page(page, aaa);
        result.getRecords().forEach(item -> {
            item.setUserId(iUserService.getOne(new QueryWrapper<User>()
                    .eq("user_id", item.getUserId())).getNickName());
        });

        return Result.success().code(ResultCode.SUCCESS).message("查询成功").data(result);
    }

    @PostMapping("/add")
    @ResponseBody
    public Result dailyAdd(@RequestBody String info) {
        LoginUserDto loginUserDto = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Daily daily = new Daily();
        daily.setDailyInfo(info);
        daily.setCreateTime(LocalDate.now());
        daily.setUserId(loginUserDto.getUser().getUserId());
        daily.setDepId(iDepUserService.getOne(new QueryWrapper<DepUser>()
                .eq("user_id", loginUserDto.getUser().getUserId())).getDepId());
        iDailyService.save(daily);
        return Result.success().data("发布成功!");
    }


    @OperationLog("修改")
    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "修改")
    @PreAuthorize("hasAnyAuthority('daily:edit')")
    @Transactional
    public Result dailyEdit(@RequestBody @Valid Daily daily, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error().message(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Result result = iDailyService.updateDaily(daily);
        return result;
    }


    @OperationLog("删除")
    @PostMapping("/del")
    @ResponseBody
    @ApiOperation("删除")
    @PreAuthorize("hasAnyAuthority('daily:del')")
    @Transactional
    public Result deleteDaily(@RequestBody List<Daily> dailyList) {
        Result result = iDailyService.deleteDaily(dailyList);
        return result;
    }
}
