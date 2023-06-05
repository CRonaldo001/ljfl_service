package io.renren.modules.AppType.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.context.TenantContext;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.constant.Constant;
import io.renren.modules.AppType.dao.AppTypeDao;
import io.renren.modules.AppType.dto.AppTypeDTO;
import io.renren.modules.AppType.entity.AppTypeEntity;
import io.renren.modules.AppType.service.AppTypeService;
import io.renren.modules.security.user.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 垃圾类型
 *
 * @author WEI 
 * @since 3.0 2022-08-30
 */
@Service
public class AppTypeServiceImpl extends CrudServiceImpl<AppTypeDao, AppTypeEntity, AppTypeDTO> implements AppTypeService {

    @Override
    public QueryWrapper<AppTypeEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<AppTypeEntity> wrapper = new QueryWrapper<>();


        wrapper.eq("tenant_code", TenantContext.getTenantCode(SecurityUser.getUser()));



        return wrapper;
    }


}