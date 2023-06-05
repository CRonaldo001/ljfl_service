package io.renren.modules.AppUserCode.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.AppUserCode.dto.AppUserCodeDTO;
import io.renren.modules.AppUserCode.entity.AppUserCodeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 用户验证码
*
* @author WEI 
* @since 3.0 2022-08-23
*/
@Mapper
public interface AppUserCodeDao extends BaseDao<AppUserCodeEntity> {
    void deleteByUserId(String  phone);
    List<AppUserCodeDTO> getCode(String phone, String code);
}