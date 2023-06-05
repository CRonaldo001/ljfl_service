package io.renren.modules.AppSchoolBuilding.service;

import io.renren.common.service.CrudService;
import io.renren.modules.AppSchoolBuilding.dto.AppSchoolBuildingDTO;
import io.renren.modules.AppSchoolBuilding.entity.AppSchoolBuildingEntity;

import java.util.List;
import java.util.Map;

/**
 * 楼栋
 *
 * @author WEI
 * @since 3.0 2023-03-20
 */
public interface AppSchoolBuildingService extends CrudService<AppSchoolBuildingEntity, AppSchoolBuildingDTO> {
    List<AppSchoolBuildingDTO> getListBySchoolId(Map<String, Object> params);
}