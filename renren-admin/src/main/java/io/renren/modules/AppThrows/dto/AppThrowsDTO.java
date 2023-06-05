package io.renren.modules.AppThrows.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 投放记录
*
* @author WEI 
* @since 3.0 2022-08-29
*/
@Data
@ApiModel(value = "投放记录")
public class AppThrowsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "租户编码")
    private Long tenantCode;
    @ApiModelProperty(value = "创建者")
    private Long creator;

    @ApiModelProperty(value = "更新者")
    private Long updater;
    @ApiModelProperty(value = "更新时间")
    private Date updateDate;


    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    /**
     * 桶号
     */
    private Integer deviceNo;
    /**
     * 刷卡号
     */
    private String swipeNo;
    /**
     * 垃圾类型编号
     */
    private String garbageTypeCode;

    /**
     * 重量
     */
    private Double weight;
    private String deviceId;

}