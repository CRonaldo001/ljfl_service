package io.renren.modules.AppSign.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.AppSign.dto.AppSignDTO;
import io.renren.modules.AppSign.entity.AppSignEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 签到
*
* @author WEI 
* @since 3.0 2022-08-26
*/
@Mapper
public interface AppSignDao extends BaseDao<AppSignEntity> {
    List<AppSignDTO> getSign(Long id);

    List<String> getLast7Sign(Long id);

    void updateInfo(Long id);
}