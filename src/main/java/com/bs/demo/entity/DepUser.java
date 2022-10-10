package com.bs.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 部门用户表
 * </p>
 *
 * @author gf
 * @since 2022-05-26
 */
@Data
@TableName("dep_user")
@ApiModel(value = "DepUser对象", description = "部门用户表")
public class DepUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门用户编号")
      @TableId(value = "dep_user_id", type = IdType.AUTO)
    private Integer depUserId;

    @ApiModelProperty("部门编号")
    private Integer depId;

    @ApiModelProperty("用户编号")
    private Object userId;

}
