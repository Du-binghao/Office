package com.bs.demo.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;
import com.bs.demo.common.SearchOption;

import com.bs.demo.dto.LoginUserDto;
import com.bs.demo.entity.Dep;
import com.bs.demo.entity.DepUser;
import com.bs.demo.entity.Res;
import com.bs.demo.service.IDepService;
import com.bs.demo.service.IDepTaskService;
import com.bs.demo.entity.DepTask;


import com.bs.demo.service.IDepUserService;
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
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gf
 * @since 2022-05-26
 */
@Controller
@RequestMapping("/task")
@RestController
@Api(tags = "")
public class DepTaskController {

    @Autowired
    public IDepTaskService iDepTaskService;

    @Autowired
    public IDepService iDepService;

    @Autowired
    public IDepUserService iDepUserService;

    @GetMapping("/myTask")
    public Result myTask(){
        LoginUserDto loginUserDto = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Integer depId = iDepUserService.getOne(new QueryWrapper<DepUser>()
                .eq("user_id", loginUserDto.getUser().getUserId())).getDepId();

        List<DepTask> deptasks = iDepTaskService.list(new QueryWrapper<DepTask>()
                .eq("dep_id", depId));

        if (deptasks.size() == 0){
            return Result.error().data("暂无任务!");
        } else {
            return Result.success().data(deptasks.get(deptasks.size() -1));
        }
    }


    @PostMapping("/list")
    @ResponseBody
    public Result depTaskList(@RequestBody JSONObject jsonObject) {
        Integer current = jsonObject.get("current",Integer.class);
        QueryWrapper<DepTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        Page<DepTask> page = new Page<>(current,10);
        Page<DepTask> result = iDepTaskService.page(page, queryWrapper);
        result.getRecords().forEach(item-> {
            Dep dep = iDepService.getById((Serializable) item.getDepId());

            item.setDepId(dep.getDepName());
        });

        return Result.success().code(ResultCode.SUCCESS).message("查询成功").data(result);
    }

    @PostMapping("/add")
    @ResponseBody
    public Result depTaskAdd(@RequestBody DepTask depTask) {

        Result result = iDepTaskService.addDepTask(depTask);
        return result;
    }


    @OperationLog("删除")
    @PostMapping("/del")
    @ResponseBody
    public Result deleteDepTask(@RequestBody List<DepTask> depTaskList) {
        Result result = iDepTaskService.deleteDepTask(depTaskList);
        return result;
    }
}
