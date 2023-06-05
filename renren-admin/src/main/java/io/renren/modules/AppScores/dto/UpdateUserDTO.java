package io.renren.modules.AppScores.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateUserDTO {
    @ApiModelProperty(value = "phone")
    private String phone;
    @ApiModelProperty(value = "name")
    private String name;
    @ApiModelProperty(value = "value")
    private String value;
    @ApiModelProperty(value = "code")
    private String code;

}
