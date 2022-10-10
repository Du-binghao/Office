package com.bs.demo.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;
import com.bs.demo.common.SearchOption;

import com.bs.demo.dto.LoginUserDto;
import com.bs.demo.service.ICommentService;
import com.bs.demo.entity.Comment;


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
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author gf
 * @since 2022-05-27
 */
@Controller
@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    public ICommentService iCommentService;


    @GetMapping("/info")
    public Result getInfo(Integer postsId) {
        return Result.success().data(iCommentService.list(new QueryWrapper<Comment>()
                .eq("posts_id", postsId)));
    }

    @PostMapping("/addComment")
    public Result addComment(@RequestBody JSONObject jsonObject) {
        String commentInfo = jsonObject.get("commentInfo",String.class);
        String postsId = jsonObject.get("postsId",String.class);
        Comment comment = new Comment();
        LoginUserDto loginUserDto = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        comment.setCommentInfo(commentInfo);
        comment.setUserId(loginUserDto.getUser().getUserId().toString());
        comment.setPostsId(postsId);
        comment.setCreateTime(LocalDateTime.now());
        iCommentService.save(comment);
        return Result.success().data(iCommentService.list(new QueryWrapper<Comment>()
                .eq("posts_id", postsId)));
    }

    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "列表")
    @PreAuthorize("hasAnyAuthority('comment:list')")
    public Result commentList(@RequestBody SearchOption searchOption) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        Page<Comment> page = new Page<>(searchOption.getCurrentPage(), searchOption.getPageSize());
        queryWrapper.like("comment_name", searchOption.getSearchText());
        return Result.success().code(ResultCode.SUCCESS).message("查询成功").data(iCommentService.page(page, queryWrapper));
    }

    @OperationLog("添加")
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加")
    @PreAuthorize("hasAnyAuthority('comment:add')")
    @Transactional
    public Result commentAdd(@RequestBody @Valid Comment comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error().message(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Result result = iCommentService.addComment(comment);
        return result;
    }


    @OperationLog("修改")
    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "修改")
    @PreAuthorize("hasAnyAuthority('comment:edit')")
    @Transactional
    public Result commentEdit(@RequestBody @Valid Comment comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error().message(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Result result = iCommentService.updateComment(comment);
        return result;
    }


    @OperationLog("删除")
    @PostMapping("/del")
    @ResponseBody
    @ApiOperation("删除")
    @PreAuthorize("hasAnyAuthority('comment:del')")
    @Transactional
    public Result deleteComment(@RequestBody List<Comment> commentList) {
        Result result = iCommentService.deleteComment(commentList);
        return result;
    }
}
