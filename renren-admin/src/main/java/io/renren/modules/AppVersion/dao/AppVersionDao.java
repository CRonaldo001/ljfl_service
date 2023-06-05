package io.renren.modules.AppVersion.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.AppScores.dto.AppScoresDTO;
import io.renren.modules.AppVersion.dto.AppVersionDTO;
import io.renren.modules.AppVersion.entity.AppVersionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * app版本
 *
 * @author WEI
 * @since 3.0 2022-09-20
 */
@Mapper
public interface AppVersionDao extends BaseDao<AppVersionEntity> {
    List<AppVersionDTO> getList(String paltform);
}