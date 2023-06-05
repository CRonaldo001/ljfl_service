package io.renren.modules.AppThrows.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class ThrowsDemo {
    @ApiModelProperty(value = "创建时间")
    private String createDate;

    @ApiModelProperty(value = "桶号")
    private Integer no;
    @ApiModelProperty(value = "刷卡号")
    private String swipeNo;
    @ApiModelProperty(value = "垃圾类型编号")
    private Integer garbageTypeCode;
    @ApiModelProperty(value = "垃圾类型")
    private String garbageTypeName;
    @ApiModelProperty(value = "手机号")
    private String mobilePhone;
    @ApiModelProperty(value = "姓名")
    private String realName;
    @ApiModelProperty(value = "重量")
    private Double weight;
    @ApiModelProperty(value = "照片")
    private String deviceImg;
}
