package io.renren.modules.AppThrows.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.context.TenantContext;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.constant.Constant;
import io.renren.modules.AppThrows.dao.AppThrowsDao;
import io.renren.modules.AppThrows.dto.AppThrowsDTO;
import io.renren.modules.AppThrows.entity.AppThrowsEntity;
import io.renren.modules.AppThrows.service.AppThrowsService;
import io.renren.modules.security.user.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 投放记录
 *
 * @author WEI 
 * @since 3.0 2022-08-29
 */
@Service
public class AppThrowsServiceImpl extends CrudServiceImpl<AppThrowsDao, AppThrowsEntity, AppThrowsDTO> implements AppThrowsService {

    @Override
    public QueryWrapper<AppThrowsEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<AppThrowsEntity> wrapper = new QueryWrapper<>();

        wrapper.eq("tenant_code", TenantContext.getTenantCode(SecurityUser.getUser()));




        return wrapper;
    }


}