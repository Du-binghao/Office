package com.bs.demo.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;
import com.bs.demo.common.SearchOption;

import com.bs.demo.dto.LoginUserDto;
import com.bs.demo.service.IPostsService;
import com.bs.demo.entity.Posts;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author gf
 * @since 2022-05-27
 */
@Controller
@RequestMapping("/posts")
@RestController
@Api(tags = "")
public class PostsController {

    @Autowired
    public IPostsService iPostsService;

    @PostMapping("/addPosts")
    public Result addPosts(@RequestBody JSONObject jsonObject) {
        String code = jsonObject.get("code", String.class);
        String postsTitle = jsonObject.get("postsTitle", String.class);
        String pwd = jsonObject.get("pwd", String.class);
        LoginUserDto loginUserDto = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String users = "{" + loginUserDto.getUser().getUserId() + "}";

        if (code.equals("0")){
            Posts posts = new Posts();
            posts.setUsers(users);
            posts.setPostsTitle(postsTitle);
            posts.setPwd(pwd);
            posts.setCreateTime(LocalDateTime.now());
            iPostsService.save(posts);
            return Result.success().data("???????????????");
        } else {
            Posts one = iPostsService.getOne(new QueryWrapper<Posts>()
                    .eq("posts_title", postsTitle)
                    .eq("pwd", pwd));
            if (one != null) {
                if (one.getUsers().toString().contains("{"+loginUserDto.getUser().getUserId()+"}")){
                    return Result.error().data("????????????????????????");
                } else {
                    String newUsers = one.getUsers()+"{"+loginUserDto.getUser().getUserId()+"}";
                    iPostsService.update(new UpdateWrapper<Posts>()
                            .eq("posts_id",one.getPostsId())
                            .set("users",newUsers));
                    return Result.success().data("???????????????");
                }
            } else {
                return Result.error().data("????????????????????????????????????");
            }



        }

    }


    @GetMapping("/postsList")
    public Result postsLists() {
        LoginUserDto loginUserDto = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Posts> users = iPostsService.list(new QueryWrapper<Posts>()
                .orderByDesc("create_time")
                .like("users", "{" + loginUserDto.getUser().getUserId() + "}"));
        users.forEach(item -> {
            List<Integer> userIds = new ArrayList<>();
            for (int i = 1; i < item.getUsers().toString().length(); i += 3) {
                char c = item.getUsers().toString().toCharArray()[i];
                userIds.add(Integer.parseInt(c + ""));
            }
            item.setUsers(userIds);
        });
        return Result.success().data(users);
    }


    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "??????")
    @PreAuthorize("hasAnyAuthority('posts:list')")
    public Result postsList(@RequestBody SearchOption searchOption) {
        QueryWrapper<Posts> queryWrapper = new QueryWrapper<>();
        Page<Posts> page = new Page<>(searchOption.getCurrentPage(), searchOption.getPageSize());
        queryWrapper.like("posts_name", searchOption.getSearchText());
        return Result.success().code(ResultCode.SUCCESS).message("????????????").data(iPostsService.page(page, queryWrapper));
    }

    @OperationLog("??????")
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "??????")
    @PreAuthorize("hasAnyAuthority('posts:add')")
    @Transactional
    public Result postsAdd(@RequestBody @Valid Posts posts, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error().message(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Result result = iPostsService.addPosts(posts);
        return result;
    }


    @OperationLog("??????")
    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "??????")
    @PreAuthorize("hasAnyAuthority('posts:edit')")
    @Transactional
    public Result postsEdit(@RequestBody @Valid Posts posts, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error().message(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Result result = iPostsService.updatePosts(posts);
        return result;
    }


    @OperationLog("??????")
    @PostMapping("/del")
    @ResponseBody
    @ApiOperation("??????")
    @PreAuthorize("hasAnyAuthority('posts:del')")
    @Transactional
    public Result deletePosts(@RequestBody List<Posts> postsList) {
        Result result = iPostsService.deletePosts(postsList);
        return result;
    }
}
