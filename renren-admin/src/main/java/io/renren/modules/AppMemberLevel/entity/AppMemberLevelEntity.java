package io.renren.modules.AppMemberLevel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 积分等级
 *
 * @author cxy 
 * @since 3.0 2022-08-26
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("app_member_level")
public class AppMemberLevelEntity {
	private static final long serialVersionUID = 1L;

	/**
	* id
	*/
	@TableId
	private Long id;
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
	* 等级
	*/
	private String level;
	/**
	* 等级名称
	*/
	private String levelName;
	/**
	* 积分下限
	*/
	private Integer integralLower;
	/**
	* 积分上限
	*/
	private Integer integralUpper;
	/**
	* 积分转让费率
	*/
	private Double serviceCharge;
	/**
	* 备注
	*/
	private String remark;
}