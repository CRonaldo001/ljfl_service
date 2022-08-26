package io.renren.modules.AppMemberLevel.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.AppMemberLevel.entity.AppMemberLevelEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 积分等级
*
* @author cxy 
* @since 3.0 2022-08-26
*/
@Mapper
public interface AppMemberLevelDao extends BaseDao<AppMemberLevelEntity> {
	
}