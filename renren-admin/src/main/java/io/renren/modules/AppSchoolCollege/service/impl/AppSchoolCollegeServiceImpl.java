package io.renren.modules.AppSchoolCollege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.context.TenantContext;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.constant.Constant;
import io.renren.modules.AppSchoolCollege.dao.AppSchoolCollegeDao;
import io.renren.modules.AppSchoolCollege.dto.AppSchoolCollegeDTO;
import io.renren.modules.AppSchoolCollege.entity.AppSchoolCollegeEntity;
import io.renren.modules.AppSchoolCollege.service.AppSchoolCollegeService;
import io.renren.modules.security.user.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 学院
 *
 * @author WEI
 * @since 3.0 2023-03-20
 */
@Service
public class AppSchoolCollegeServiceImpl extends CrudServiceImpl<AppSchoolCollegeDao, AppSchoolCollegeEntity, AppSchoolCollegeDTO> implements AppSchoolCollegeService {

    @Override
    public QueryWrapper<AppSchoolCollegeEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<AppSchoolCollegeEntity> wrapper = new QueryWrapper<>();


        wrapper.eq("tenant_code", TenantContext.getTenantCode(SecurityUser.getUser()));


        return wrapper;
    }


    @Override
    public List<AppSchoolCollegeDTO> getListBySchoolId(Map<String, Object> params) {


        return baseDao.getListBySchoolId(params);
    }
}