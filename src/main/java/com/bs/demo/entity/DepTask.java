package com.bs.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author gf
 * @since 2022-05-26
 */
@Data
@TableName("dep_task")
@ApiModel(value = "DepTask对象", description = "")
public class DepTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("任务编号")
      @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    @ApiModelProperty("任务描述")
    private String taskInfo;

    @ApiModelProperty("任务标题")
    private String taskTitle;

    @ApiModelProperty("部门编号")
    private Object depId;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


}
