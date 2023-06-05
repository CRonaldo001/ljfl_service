package io.renren.modules.AppThrows.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ThrowsDTO {
    @ApiModelProperty(value = "桶号")
    private Integer deviceNo;
    @ApiModelProperty(value = "刷卡号")
    private String swipeNo;
    @ApiModelProperty(value = "垃圾类型编号")
    private String garbageTypeCode;
    private Double weight;
    private String id;

}
