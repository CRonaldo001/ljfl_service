package io.renren.modules.AppSchoolCollege.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 学院
 *
 * @author WEI 
 * @since 3.0 2023-03-20
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("app_school_college")
public class AppSchoolCollegeEntity {
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
	private Long schoolId;
	/**
	* 学院名称
	*/
	private String collegeName;
	/**
	* 备注
	*/
	private String remark;
}