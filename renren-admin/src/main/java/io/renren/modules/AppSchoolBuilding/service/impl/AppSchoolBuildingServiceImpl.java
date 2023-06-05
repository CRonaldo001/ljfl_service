package io.renren.modules.AppSchoolBuilding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.context.TenantContext;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.constant.Constant;
import io.renren.modules.AppSchoolBuilding.dao.AppSchoolBuildingDao;
import io.renren.modules.AppSchoolBuilding.dto.AppSchoolBuildingDTO;
import io.renren.modules.AppSchoolBuilding.entity.AppSchoolBuildingEntity;
import io.renren.modules.AppSchoolBuilding.service.AppSchoolBuildingService;
import io.renren.modules.security.user.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 楼栋
 *
 * @author WEI
 * @since 3.0 2023-03-20
 */
@Service
public class AppSchoolBuildingServiceImpl extends CrudServiceImpl<AppSchoolBuildingDao, AppSchoolBuildingEntity, AppSchoolBuildingDTO> implements AppSchoolBuildingService {
    @Override
    public QueryWrapper<AppSchoolBuildingEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<AppSchoolBuildingEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("tenant_code", TenantContext.getTenantCode(SecurityUser.getUser()));
        return wrapper;
    }


    @Override
    public List<AppSchoolBuildingDTO> getListBySchoolId(Map<String, Object> params) {

        return baseDao.getListBySchoolId(params);
    }
}