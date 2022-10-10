package com.bs.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author gf
 * @since 2022-05-25
 */
@ApiModel(value = "Res对象", description = "")
public class Res implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("资源编号")
      @TableId(value = "res_id", type = IdType.AUTO)
    private Integer resId;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("资源地址")
    private String resAddress;

    @ApiModelProperty("资源描述")
    private String resTitle;

    @ApiModelProperty("下载次数")
    private Integer resDownload;


    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResAddress() {
        return resAddress;
    }

    public void setResAddress(String resAddress) {
        this.resAddress = resAddress;
    }

    public String getResTitle() {
        return resTitle;
    }

    public void setResTitle(String resTitle) {
        this.resTitle = resTitle;
    }

    public Integer getResDownload() {
        return resDownload;
    }

    public void setResDownload(Integer resDownload) {
        this.resDownload = resDownload;
    }

    @Override
    public String toString() {
        return "Res{" +
        "resId=" + resId +
        ", userId=" + userId +
        ", resAddress=" + resAddress +
        ", resTitle=" + resTitle +
        ", resDownload=" + resDownload +
        "}";
    }
}
