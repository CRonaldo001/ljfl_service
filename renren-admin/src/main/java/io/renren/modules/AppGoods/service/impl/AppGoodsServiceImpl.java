package io.renren.modules.AppGoods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.common.context.TenantContext;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.modules.AppArticles.dto.AppArticlesDTO;
import io.renren.modules.AppArticles.entity.AppArticlesEntity;
import io.renren.modules.AppGoods.dao.AppGoodsDao;
import io.renren.modules.AppGoods.dto.AppGoodsDTO;
import io.renren.modules.AppGoods.entity.AppGoodsEntity;
import io.renren.modules.AppGoods.service.AppGoodsService;
import io.renren.modules.security.user.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品管理
 *
 * @author WEI
 * @since 3.0 2022-08-13
 */
@Service
public class AppGoodsServiceImpl extends CrudServiceImpl<AppGoodsDao, AppGoodsEntity, AppGoodsDTO> implements AppGoodsService {

    @Override
    public QueryWrapper<AppGoodsEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<AppGoodsEntity> wrapper = new QueryWrapper<>();


        wrapper.eq("tenant_code", TenantContext.getTenantCode(SecurityUser.getUser()));


        return wrapper;
    }


    @Override
    public List<AppGoodsDTO> getGoodsList(String type, String goodsName,String goodsType) {

        if (StringUtils.isNotEmpty(goodsName)) {
            goodsName = "%" + goodsName + "%";
        }

        List<AppGoodsDTO> goodsList = this.baseDao.getGoodsList(type, goodsName,goodsType);

        return goodsList;
    }

    @Override
    public PageData<AppGoodsDTO> page(Map<String, Object> params) {
        paramsToLike(params, "name");
        //分页
        IPage<AppGoodsEntity> page = getPage(params, "create_date", false);

        //查询
        List<AppGoodsEntity> list = baseDao.getList(params);

        return getPageData(list, page.getTotal(), AppGoodsDTO.class);
    }

}