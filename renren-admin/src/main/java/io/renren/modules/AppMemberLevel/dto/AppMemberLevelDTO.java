package io.renren.modules.AppMemberLevel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* 积分等级
*
* @author cxy 
* @since 3.0 2022-08-26
*/
@Data
@ApiModel(value = "积分等级")
public class AppMemberLevelDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "租户编码")
    private Long tenantCode;
    @ApiModelProperty(value = "创建者")
    private Long creator;
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "更新者")
    private Long updater;
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @ApiModelProperty(value = "更新时间")
    private Date updateDate;
    @ApiModelProperty(value = "等级")
    private String level;
    @ApiModelProperty(value = "等级名称")
    private String levelName;
    @ApiModelProperty(value = "积分下限")
    private Integer integralLower;
    @ApiModelProperty(value = "积分上限")
    private Integer integralUpper;
    @ApiModelProperty(value = "积分转让费率")
    private Double serviceCharge;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "备注")
    private List<AppMemberLevelDTO> list;

}