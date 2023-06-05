package io.renren.modules.AppScores.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PhoneDTO {
    @ApiModelProperty(value = "phone")
    private String phone;
}
