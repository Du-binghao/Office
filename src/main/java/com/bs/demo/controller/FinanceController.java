package com.bs.demo.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;
import com.bs.demo.common.SearchOption;

import com.bs.demo.dto.LoginUserDto;
import com.bs.demo.entity.User;
import com.bs.demo.service.IFinanceService;
import com.bs.demo.entity.Finance;


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

/**
 *
 * @author gf
 * @since 2022-05-26
 */
@Controller
@RestController
@RequestMapping("/finance")
@Api(tags = "")
public class FinanceController {

    @Autowired
    public IFinanceService iFinanceService;

    @Autowired
    public IUserService iUserService;



    @PostMapping("/editCode")
    public Result editFinanceCode(@RequestBody JSONObject jsonObject){
        Integer financeId = jsonObject.get("financeId",Integer.class);
        iFinanceService.update(new UpdateWrapper<Finance>()
                .eq("finance_id",financeId)
                .set("code","chexiao"));
        return Result.success().data("撤销成功!");
    }

    @GetMapping("/userFinance")
    public Result getUserFinance(){
        LoginUserDto loginUserDto = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Finance> result = iFinanceService.list(new QueryWrapper<Finance>()
                .eq("user_id", loginUserDto.getUser().getUserId())
                .orderByDesc("create_time"));
        return Result.success().data(result);

    }

    @PostMapping("/edit_code")
    public Result editCode(@RequestBody JSONObject jsonObject){
        Integer code = jsonObject.get("code",Integer.class);
        Integer financeId = jsonObject.get("financeId",Integer.class);
        iFinanceService.update(new UpdateWrapper<Finance>()
                .eq("finance_id",financeId)
                .set("code",code));
        return Result.success().code(ResultCode.SUCCESS).message("操作成功!");

    }


    @GetMapping("/list")
    @ResponseBody
    public Result financeList(Integer current) {
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        Page<Finance> page = new Page<>(current, 10);
        queryWrapper.orderByDesc("create_time");
        Page<Finance> result = iFinanceService.page(page, queryWrapper);
        result.getRecords().forEach(item-> {
            item.setUserId(iUserService.getOne(new QueryWrapper<User>()
                    .eq("user_id",item.getUserId())).getNickName());
        });
        return Result.success().code(ResultCode.SUCCESS).message("查询成功").data(result);
    }

    @PostMapping("/add")
    @ResponseBody
    public Result financeAdd(@RequestBody Finance finance) {
        Result result = iFinanceService.addFinance(finance);
        return result;
    }


    @OperationLog("修改")
    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "修改")
    @PreAuthorize("hasAnyAuthority('finance:edit')")
    @Transactional
    public Result financeEdit(@RequestBody @Valid Finance finance,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error().message(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Result result = iFinanceService.updateFinance(finance);
        return result;
    }



    @OperationLog("删除")
    @PostMapping("/del")
    @ResponseBody
    @ApiOperation("删除")
    @PreAuthorize("hasAnyAuthority('finance:del')")
    @Transactional
    public Result deleteFinance(@RequestBody List<Finance> financeList) {
        Result result = iFinanceService.deleteFinance(financeList);
        return result;
    }
}
