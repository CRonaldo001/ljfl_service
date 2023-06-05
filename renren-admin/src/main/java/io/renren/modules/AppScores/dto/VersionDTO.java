package io.renren.modules.AppScores.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class VersionDTO {
    @ApiModelProperty(value = "appid")
    private String appid;
    @ApiModelProperty(value = "appVersion")
    private String appVersion;
    @ApiModelProperty(value = "wgtVersion")
    private String wgtVersion;
    @ApiModelProperty(value = "paltform")
    private String paltform;

}
