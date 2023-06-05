package io.renren.modules.AppVersion.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * app版本
 *
 * @author WEI 
 * @since 3.0 2022-09-20
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("app_version")
public class AppVersionEntity {
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