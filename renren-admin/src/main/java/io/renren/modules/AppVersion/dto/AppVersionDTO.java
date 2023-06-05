package io.renren.modules.AppVersion.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* app版本
*
* @author WEI 
* @since 3.0 2022-09-20
*/
@Data
@ApiModel(value = "app版本")
public class AppVersionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "租户编码")
    private Long tenantCode;
    @ApiModelProperty(value = "创建者")
    private Long creator;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "更新者")
    private Long updater;
    @ApiModelProperty(value = "更新时间")
    private Date updateDate;
    private String appid;
    private String contents;
    private Integer isMandatory;
    private Integer isSilently;
    private String name;
    private String paltform;
    private Integer stablePublish;
    private String title;
    private String type;
    private String url;
    private String version;

}