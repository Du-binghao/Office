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
 * 聊天室消息表
 * </p>
 *
 * @author gf
 * @since 2022-05-27
 */
@Data
@ApiModel(value = "Comment对象", description = "聊天室消息表")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("聊天室编号")
    private String postsId;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("发布内容")
    private String commentInfo;

    @ApiModelProperty("评论时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("聊天室消息编号")
      @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;


}
