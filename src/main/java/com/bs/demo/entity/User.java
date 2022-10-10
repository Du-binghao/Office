package com.bs.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author gf
 * @since 2021-12-28
 */
@Data
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId(type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空！")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "只能输入英文字母或数字！")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    @NotBlank(message = "昵称不能为空！")
    private String nickName;

    @ApiModelProperty("性别")
    @NotBlank(message = "性别不能为空！")
    private String sex;

    @ApiModelProperty("电话")
    @NotBlank(message = "电话不能为空！")
    private String phone;

    @ApiModelProperty("邮箱")
    @NotBlank(message = "邮箱不能为空！")
    @Email
    private String email;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("状态")
    @NotNull(message = "状态不能为空！")
    private Integer status;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", update = "now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @TableField(select = false)
    private Integer roleId;
    @TableField(select = false)
    private String roleName;
    @TableField(select = false)
    private String description;

    public interface Status {
        int LOCKED = 0;
        int VALID = 1;
    }
}
