package io.renren.modules.AppScores.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 积分记录
*
* @author WEI 
* @since 3.0 2022-08-13
*/
@Data
@ApiModel(value = "积分记录")
public class AddScoresDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "环保积分")
    private Integer normalScore;
    @ApiModelProperty(value = "实践积分")
    private Integer specialScore;
    @ApiModelProperty(value = "积分产生类型")
    private String type;
    @ApiModelProperty(value = "重量")
    private Double weight;
    @ApiModelProperty(value = "电话")
    private String phone;
}