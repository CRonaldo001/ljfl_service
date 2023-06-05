package io.renren.modules.AppType.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.AppType.entity.AppTypeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 垃圾类型
*
* @author WEI 
* @since 3.0 2022-08-30
*/
@Mapper
public interface AppTypeDao extends BaseDao<AppTypeEntity> {
	
}