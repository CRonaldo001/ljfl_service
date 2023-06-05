package io.renren.modules.AppSchool.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.AppSchool.dto.AppSchoolDTO;
import io.renren.modules.AppSchool.entity.AppSchoolEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 学校
*
* @author WEI 
* @since 3.0 2023-03-20
*/
@Mapper
public interface AppSchoolDao extends BaseDao<AppSchoolEntity> {
    List<AppSchoolDTO> getListSchool();
}