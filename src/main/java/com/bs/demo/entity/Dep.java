package com.bs.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 部门表

 * </p>
 *
 * @author gf
 * @since 2022-05-26
 */
@Data
@ApiModel(value = "Dep对象", description = "部门表 ")
public class Dep implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门编号")
      @TableId(value = "dep_id", type = IdType.AUTO)
    private Integer depId;

    @ApiModelProperty("部门信息")
    private String depInfo;

    @ApiModelProperty("部门名")
    private String depName;

    @ApiModelProperty("部门经理")
    private String depManager;

}
