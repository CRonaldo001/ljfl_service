package io.renren.modules.AppSchoolCollege.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.AppSchoolCollege.dto.AppSchoolCollegeDTO;
import io.renren.modules.AppSchoolCollege.entity.AppSchoolCollegeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* 学院
*
* @author WEI 
* @since 3.0 2023-03-20
*/
@Mapper
public interface AppSchoolCollegeDao extends BaseDao<AppSchoolCollegeEntity> {
    List<AppSchoolCollegeDTO> getListBySchoolId(Map<String, Object> params);

}