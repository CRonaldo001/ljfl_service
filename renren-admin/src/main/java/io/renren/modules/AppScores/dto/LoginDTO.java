package io.renren.modules.AppScores.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDTO {
    @ApiModelProperty(value = "phone")
    private String phone;
    @ApiModelProperty(value = "password")
    private String password;
}
