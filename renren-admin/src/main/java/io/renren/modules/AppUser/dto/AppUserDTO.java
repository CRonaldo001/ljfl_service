package io.renren.modules.AppUser.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* APP用户
*
* @author WEI 
* @since 3.0 2022-08-22
*/
@Data
@ApiModel(value = "APP用户")
public class AppUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "租户编码")
    private Long tenantCode;
    @ApiModelProperty(value = "创建者")
    private Long creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "更新者")
    private Long updater;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;
    @ApiModelProperty(value = "人员类型")
    private String type;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "手机号码")
    private String phoneNum;


    @ApiModelProperty(value = "对方邀请手机号码")
    private String inviteNum;

    @ApiModelProperty(value = "学校名称")
    private String schoolName;
    @ApiModelProperty(value = "专业")
    private String professional;
    @ApiModelProperty(value = "班级")
    private String classes;
    @ApiModelProperty(value = "班級")
    private String studentNum;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "寝室")
    private String room;
    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "状态")
    private String status;
    private Long schoolId;
    private Long buildId;
    private Long collegeId;
    private String sex;
    private String buildName;
    private String collegeName;
}