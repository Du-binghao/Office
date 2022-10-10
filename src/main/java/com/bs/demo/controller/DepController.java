package com.bs.demo.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.bs.demo.common.Result;
import com.bs.demo.common.ResultCode;
import com.bs.demo.common.SearchOption;

import com.bs.demo.entity.User;
import com.bs.demo.service.IDepService;
import com.bs.demo.entity.Dep;


import com.bs.demo.service.IUserService;
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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author gf
 * @since 2022-05-26
 */
@Controller
@RestController
@RequestMapping("/dep")
@Api(tags = "")
public class DepController {

    @Autowired
    public IDepService iDepService;

    @Autowired
    public IUserService iUserService;

    /**
     * 获取所有部门
     *
     * @return
     */
    @GetMapping("/get_all_dep")
    public Result getAllDep() {
        List<Dep> deps = iDepService.list();
        deps.forEach(item -> {
            item.setDepManager(iUserService.getOne(new QueryWrapper<User>()
                    .eq("user_id",item.getDepManager())).getNickName() + "||" + item.getDepManager());
        });
        return Result.success().data(deps);
    }



    /**
     * 添加部门
     *
     * @return
     */
    @PostMapping("/add_dep")
    public Result addDep(@RequestBody Dep department) {
        iDepService.save(department);
        return Result.success().data("添加成功！");
    }


    /**
     * 修改部门信息
     *
     * @param depInfo
     * @return
     */
    @PostMapping("/edit_dep")
    public Result editDep(@RequestBody JSONObject depInfo) {
        String depOldName = iDepService.getOne(new QueryWrapper<Dep>()
                .eq("dep_id",depInfo.get("depId"))
        ).getDepName();

        boolean bool = iDepService.update(new UpdateWrapper<Dep>()
                .eq("dep_id", depInfo.get("depId"))
                .set("dep_name", depInfo.get("depName"))
                .set("dep_info", depInfo.get("depInfo"))
                .set("dep_manager", depInfo.get("depManager"))
        );
        return Result.success().data("修改成功！");
    }


    /**
     * 删除部门
     *
     * @return
     */
    @PostMapping("/del_dep")
    public Result delDep(@RequestBody List<Dep> departments) {
        List<Integer> ids = departments.stream().map(Dep::getDepId).collect(Collectors.toList());

        iDepService.remove(new QueryWrapper<Dep>()
                .in("dep_id", ids));
        return Result.success().data("删除成功！");
    }

    @GetMapping("/get_all_users")
    public Result getAllUsers(){
        return Result.success().data(iUserService.list());
    }
}
