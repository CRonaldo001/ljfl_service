package io.renren.modules.AppScores.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GiftDTO {
    @ApiModelProperty(value = "phone")
    private String phone;
    @ApiModelProperty(value = "otherPhone")
    private String otherPhone;
    @ApiModelProperty(value = "scores")
    private Integer scores;
    @ApiModelProperty(value = "charge")
    private Double charge;
}
