package io.renren.modules.AppSchool.service;

import io.renren.common.service.CrudService;
import io.renren.modules.AppSchool.dto.AppSchoolDTO;
import io.renren.modules.AppSchool.entity.AppSchoolEntity;

import java.util.List;

/**
 * 学校
 *
 * @author WEI 
 * @since 3.0 2023-03-20
 */
public interface AppSchoolService extends CrudService<AppSchoolEntity, AppSchoolDTO> {
   List<AppSchoolDTO> getListSchool();
}