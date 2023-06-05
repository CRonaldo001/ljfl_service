package io.renren.modules.AppThrows.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.AppThrows.entity.AppThrowsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 投放记录
*
* @author WEI 
* @since 3.0 2022-08-29
*/
@Mapper
public interface AppThrowsDao extends BaseDao<AppThrowsEntity> {
	
}