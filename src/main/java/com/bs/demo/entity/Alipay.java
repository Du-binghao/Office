package com.bs.demo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 支付宝配置类
 * </p>
 *
 * @author gf
 * @since 2022-02-02
 */
@Data
@TableName("alipay")
@ApiModel(value = "Alipay对象" , description = "支付宝配置类")
public class Alipay implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "alipay_id")
    private Integer alipayId;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("商户号")
    private String pid;

    @ApiModelProperty("编码")
    private String charset;

    @ApiModelProperty("类型 固定格式json")
    private String format;

    @ApiModelProperty("网关地址")
    private String gatewayUrl;

    @ApiModelProperty("异步回调")
    private String notifyUrl;

    @ApiModelProperty("私钥")
    private String privateKey;

    @ApiModelProperty("公钥")
    private String publicKey;

    @ApiModelProperty("回调地址")
    private String returnUrl;

    @ApiModelProperty("签名方式")
    private String signType;


    public Integer getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(Integer alipayId) {
        this.alipayId = alipayId;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Alipay{" +
                "alipayId=" + alipayId +
                ", appId='" + appId + '\'' +
                ", pid='" + pid + '\'' +
                ", charset='" + charset + '\'' +
                ", format='" + format + '\'' +
                ", gatewayUrl='" + gatewayUrl + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", signType='" + signType + '\'' +
                '}';
    }
}
