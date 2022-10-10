package com.bs.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 日报表
 * </p>
 *
 * @author gf
 * @since 2022-05-26
 */
@Data
@ApiModel(value = "Daily对象", description = "日报表")
public class Daily implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("日报编号")
      @TableId(value = "daily_id", type = IdType.AUTO)
    private Integer dailyId;

    @ApiModelProperty("日报内容")
    private String dailyInfo;

    @ApiModelProperty("用户id")
    private Object userId;

    @ApiModelProperty("发布时间")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createTime;

    @ApiModelProperty("部门编号")
    private Integer depId;


}
