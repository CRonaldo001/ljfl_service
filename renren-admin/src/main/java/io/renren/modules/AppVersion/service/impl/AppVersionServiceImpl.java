package io.renren.modules.AppVersion.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.context.TenantContext;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.modules.AppVersion.dao.AppVersionDao;
import io.renren.modules.AppVersion.dto.AppVersionDTO;
import io.renren.modules.AppVersion.entity.AppVersionEntity;
import io.renren.modules.AppVersion.service.AppVersionService;
import io.renren.modules.security.user.SecurityUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * app版本
 *
 * @author WEI 
 * @since 3.0 2022-09-20
 */
@Service
public class AppVersionServiceImpl extends CrudServiceImpl<AppVersionDao, AppVersionEntity, AppVersionDTO> implements AppVersionService {

    @Override
    public QueryWrapper<AppVersionEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<AppVersionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("tenant_code", TenantContext.getTenantCode(SecurityUser.getUser()));
        return wrapper;
    }


    @Override
    public List<AppVersionDTO> getList(String paltform) {
        List<AppVersionDTO> list = this.baseDao.getList(paltform);
        return list;
    }
}