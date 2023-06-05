package io.renren.modules.AppSchoolBuilding.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.AppSchoolBuilding.dto.AppSchoolBuildingDTO;
import io.renren.modules.AppSchoolBuilding.entity.AppSchoolBuildingEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* 楼栋
*
* @author WEI 
* @since 3.0 2023-03-20
*/
@Mapper
public interface AppSchoolBuildingDao extends BaseDao<AppSchoolBuildingEntity> {
    List<AppSchoolBuildingDTO> getListBySchoolId(Map<String, Object> params);

}