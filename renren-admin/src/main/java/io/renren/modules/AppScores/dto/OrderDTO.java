package io.renren.modules.AppScores.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderDTO {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "num")
    private int num;
    @ApiModelProperty(value = "name")
    private String name;
    @ApiModelProperty(value = "phoneNum")
    private String phoneNum;
    @ApiModelProperty(value = "addr")
    private String addr;
    @ApiModelProperty(value = "sendType")
    private String sendType;
    private Long userId;

    private int totalNamorl;
}
