package io.renren.modules.AppOrder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 兑换记录
*
* @author WEI 
* @since 3.0 2022-08-13
*/
@Data
@ApiModel(value = "兑换记录")
public class AppOrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "租户编码")
    private Long tenantCode;
    @ApiModelProperty(value = "创建者")
    private Long creator;
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "更新者")
    private Long updater;
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @ApiModelProperty(value = "更新时间")
    private Date updateDate;
    @ApiModelProperty(value = "订单编号")
    private String transactionId;
    @ApiModelProperty(value = "订单状态")
    private String status;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "商品數量")
    private Integer goodsCount;
    @ApiModelProperty(value = "图片地址")
    private String url;
    @ApiModelProperty(value = "总环保积分")
    private Integer totalNamorl;
    @ApiModelProperty(value = "总实践积分")
    private Integer totalSpecial;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "是否删除")
    private String deleted;
    @ApiModelProperty(value = "删除者")
    private Long deleter;
    @ApiModelProperty(value = "删除时间")
    private Date deletedTime;
    @ApiModelProperty(value = "金额")
    private Double money;
    @ApiModelProperty(value = "兑换人")
    private String receivedName;
    @ApiModelProperty(value = "电话号码")
    private String userPhone;
    @ApiModelProperty(value = "提货方式")
    private String sendType;
    @ApiModelProperty(value = "地址")
    private String addr;
    @ApiModelProperty(value = "微信支付订单编号")
    private String outTradeNo;

}