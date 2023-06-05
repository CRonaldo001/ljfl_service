package io.renren.modules.AppUser.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.AppUser.dto.AppUserDTO;
import io.renren.modules.AppUser.entity.AppUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * APP用户
 *
 * @author WEI
 * @since 3.0 2022-08-22
 */
@Mapper
public interface AppUserDao extends BaseDao<AppUserEntity> {
    List<AppUserDTO> getUserByNum(String phone,String status);

    List<AppUserDTO> getList(Map<String, Object> params);
}