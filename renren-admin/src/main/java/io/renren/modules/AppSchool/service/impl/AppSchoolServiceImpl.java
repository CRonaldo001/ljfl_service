package io.renren.modules.AppSchool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.context.TenantContext;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.constant.Constant;
import io.renren.modules.AppSchool.dao.AppSchoolDao;
import io.renren.modules.AppSchool.dto.AppSchoolDTO;
import io.renren.modules.AppSchool.entity.AppSchoolEntity;
import io.renren.modules.AppSchool.service.AppSchoolService;
import io.renren.modules.security.user.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 学校
 *
 * @author WEI 
 * @since 3.0 2023-03-20
 */
@Service
public class AppSchoolServiceImpl extends CrudServiceImpl<AppSchoolDao, AppSchoolEntity, AppSchoolDTO> implements AppSchoolService {

    @Override
    public QueryWrapper<AppSchoolEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<AppSchoolEntity> wrapper = new QueryWrapper<>();



        wrapper.eq("tenant_code", TenantContext.getTenantCode(SecurityUser.getUser()));







        return wrapper;
    }


    @Override
    public List<AppSchoolDTO> getListSchool() {
        List<AppSchoolDTO> listSchool = baseDao.getListSchool();
        return listSchool;
    }
}