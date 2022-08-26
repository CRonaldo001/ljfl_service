package io.renren.modules.AppMemberLevel.service;

import io.renren.common.service.CrudService;
import io.renren.common.utils.Result;
import io.renren.modules.AppMemberLevel.dto.AppMemberLevelDTO;
import io.renren.modules.AppMemberLevel.entity.AppMemberLevelEntity;

/**
 * 积分等级
 *
 * @author cxy 
 * @since 3.0 2022-08-26
 */
public interface AppMemberLevelService extends CrudService<AppMemberLevelEntity, AppMemberLevelDTO> {

    Result updateLevelInfo(AppMemberLevelDTO dto);

    /**
     * 通过积分得到对应的等级dto
     * @param integral 积分
     * @return
     */
    AppMemberLevelDTO getLevelByIntegral(int integral);

}