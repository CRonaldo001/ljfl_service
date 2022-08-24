package io.renren.modules.AppOrder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.common.context.TenantContext;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.Result;
import io.renren.modules.AppArticles.dto.AppArticlesDTO;
import io.renren.modules.AppArticles.entity.AppArticlesEntity;
import io.renren.modules.AppArticles.service.AppArticlesService;
import io.renren.modules.AppOrder.dao.AppOrderDao;
import io.renren.modules.AppOrder.dto.AppOrderDTO;
import io.renren.modules.AppOrder.entity.AppOrderEntity;
import io.renren.modules.AppOrder.service.AppOrderService;
import io.renren.modules.common.constant.KxConstants;
import io.renren.modules.security.user.SecurityUser;
import liquibase.pro.packaged.D;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文章管理
 *
 * @author WEI 
 * @since 3.0 2022-08-17
 */
@Service
@Slf4j
public class AppOrderServiceImpl extends CrudServiceImpl<AppOrderDao, AppOrderEntity, AppOrderDTO> implements AppOrderService {

    @Override
    public QueryWrapper<AppOrderEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<AppOrderEntity> wrapper = new QueryWrapper<>();
//        wrapper.eq("tenant_code", TenantContext.getTenantCode(SecurityUser.getUser()));
        return wrapper;
    }


    @Override
    public List<AppOrderDTO> getOrderList(Long id) {
        return this.baseDao.getOrderList(id);
    }

    @Override
    public Result exchange(Long id) {
        AppOrderDTO appOrderDTO= this.get(id);
        if(null != appOrderDTO){
            appOrderDTO.setStatus(KxConstants.JF_YDH);
            appOrderDTO.setUpdater(TenantContext.getTenantCode(SecurityUser.getUser()));
            appOrderDTO.setUpdateDate(new Date());
            this.update(appOrderDTO);
            return new Result();
        }else {
            log.error("兑换失败，id ",id);
            return new Result().error("兑换失败，请联系管理员！");
        }

    }

    @Override
    public PageData<AppOrderDTO> page(Map<String, Object> params) {
        paramsToLike(params, "userName");
        paramsToLike(params, "userPhone");
        //分页
        IPage<AppOrderEntity> page = getPage(params, "", false);

        //查询
        List<AppOrderDTO> list = baseDao.getList(params);

        return  new PageData<>(list, page.getTotal());
    }
}