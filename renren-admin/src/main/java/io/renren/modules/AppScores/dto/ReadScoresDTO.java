package io.renren.modules.AppScores.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReadScoresDTO {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "userId")
    private Long userId;

}
