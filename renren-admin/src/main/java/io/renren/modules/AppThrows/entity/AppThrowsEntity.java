package io.renren.modules.AppThrows.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 投放记录
 *
 * @author WEI 
 * @since 3.0 2022-08-29
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("app_throws")
public class AppThrowsEntity {
	private static final long serialVersionUID = 1L;

	/**
	* id
	*/
	@TableId
	private Long id;
	/**
	* 备注
	*/
	private String remark;
	/**
	* 租户编码
	*/
	@TableField(fill = FieldFill.INSERT)
	private Long tenantCode;
	/**
	* 创建者
	*/
	@TableField(fill = FieldFill.INSERT)
	private Long creator;
	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
	private Date createDate;
	/**
	* 更新者
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;
	/**
	* 更新时间
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;

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