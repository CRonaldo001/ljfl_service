package io.renren.modules.AppSign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.context.TenantContext;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.constant.Constant;
import io.renren.modules.AppSign.dao.AppSignDao;
import io.renren.modules.AppSign.dto.AppSignDTO;
import io.renren.modules.AppSign.entity.AppSignEntity;
import io.renren.modules.AppSign.service.AppSignService;
import io.renren.modules.security.user.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 签到
 *
 * @author WEI
 * @since 3.0 2022-08-26
 */
@Service
public class AppSignServiceImpl extends CrudServiceImpl<AppSignDao, AppSignEntity, AppSignDTO> implements AppSignService {

    @Override
    public QueryWrapper<AppSignEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<AppSignEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("tenant_code", TenantContext.getTenantCode(SecurityUser.getUser()));
        return wrapper;
    }


    @Override
    public List<AppSignDTO> getSign(Long id) {
        return this.baseDao.getSign(id);
    }

    @Override
    public List<String> getLast7Sign(Long id) {
        return this.baseDao.getLast7Sign(id);
    }

    @Override
    public void updateInfo(Long id) {

        this.baseDao.updateInfo(id);
    }

}