package io.renren.modules.AppOrder.service;

import io.renren.common.page.PageData;
import io.renren.common.service.CrudService;
import io.renren.common.utils.Result;
import io.renren.modules.AppArticles.dto.AppArticlesDTO;
import io.renren.modules.AppOrder.dto.AppOrderDTO;
import io.renren.modules.AppOrder.entity.AppOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 兑换记录
 *
 * @author WEI 
 * @since 3.0 2022-08-17
 */
public interface AppOrderService extends CrudService<AppOrderEntity, AppOrderDTO> {
    List<AppOrderDTO> getOrderList(Long id);

    Result exchange(Long id);

    PageData<AppOrderDTO> page(Map<String, Object> params);

    List<AppOrderDTO> getExpList(Map<String, Object> params);

    List<AppOrderDTO> getOrderByNO(String outTradeNo);


}