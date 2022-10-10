package com.bs.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 聊天室表
 * </p>
 *
 * @author gf
 * @since 2022-05-27
 */
@Data
@ApiModel(value = "Posts对象", description = "聊天室表")
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("聊天室编号")
      @TableId(value = "posts_id", type = IdType.AUTO)
    private Integer postsId;

    @ApiModelProperty("用户编号")
    private Object users;

    @ApiModelProperty("聊天室名")
    private String postsTitle;

    @ApiModelProperty("聊天室密码")
    private String pwd;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createTime;

}
