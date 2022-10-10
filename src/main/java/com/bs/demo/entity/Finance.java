package com.bs.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 财务审核表
 * </p>
 *
 * @author gf
 * @since 2022-05-26
 */
@Data
@ApiModel(value = "Finance对象", description = "财务审核表")
public class Finance implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("财务审核编号")
      @TableId(value = "finance_id", type = IdType.AUTO)
    private Integer financeId;

    @ApiModelProperty("财务审核标题")
    private String financeTitle;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Object createTime;

    @ApiModelProperty("金额")
    private Float money;

    @ApiModelProperty("用户编号")
    private Object userId;

    @ApiModelProperty("审核状态")
    private String code;

    @ApiModelProperty("消费时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Object start;

    @ApiModelProperty("消费内容")
    private String financeInfo;

}
