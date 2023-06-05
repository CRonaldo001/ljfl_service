package io.renren.modules.AppSchoolCollege.service;

import io.renren.common.service.CrudService;
import io.renren.modules.AppSchoolCollege.dto.AppSchoolCollegeDTO;
import io.renren.modules.AppSchoolCollege.entity.AppSchoolCollegeEntity;

import java.util.List;
import java.util.Map;

/**
 * 学院
 *
 * @author WEI 
 * @since 3.0 2023-03-20
 */
public interface AppSchoolCollegeService extends CrudService<AppSchoolCollegeEntity, AppSchoolCollegeDTO> {
    List<AppSchoolCollegeDTO> getListBySchoolId(Map<String, Object> params);
}