package io.renren.modules.AppUser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.common.context.TenantContext;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.constant.Constant;
import io.renren.modules.AppGoods.dto.AppGoodsDTO;
import io.renren.modules.AppGoods.entity.AppGoodsEntity;
import io.renren.modules.AppUser.dao.AppUserDao;
import io.renren.modules.AppUser.dto.AppUserDTO;
import io.renren.modules.AppUser.entity.AppUserEntity;
import io.renren.modules.AppUser.service.AppUserService;
import io.renren.modules.security.user.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * APP用户
 *
 * @author WEI 
 * @since 3.0 2022-08-22
 */
@Service
public class AppUserServiceImpl extends CrudServiceImpl<AppUserDao, AppUserEntity, AppUserDTO> implements AppUserService {

    @Override
    public QueryWrapper<AppUserEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<AppUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("tenant_code", TenantContext.getTenantCode(SecurityUser.getUser()));
        return wrapper;
    }

    @Override
    public List<AppUserDTO> getUserByaNum(String phone,String status) {

        return this.baseDao.getUserByNum(phone,status);
    }


    @Override
    public PageData<AppUserDTO> page(Map<String, Object> params) {
        paramsToLike(params, "phoneNum");
        //分页
        IPage<AppUserEntity> page = getPage(params, "create_date", false);

        //查询
        List<AppUserDTO> list = baseDao.getList(params);

        return getPageData(list, page.getTotal(), AppUserDTO.class);
    }

}