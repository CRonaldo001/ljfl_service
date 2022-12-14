package io.renren.modules.AppArticles.service;

import io.renren.common.page.PageData;
import io.renren.common.service.CrudService;
import io.renren.modules.AppArticles.dto.AppArticlesDTO;
import io.renren.modules.AppArticles.entity.AppArticlesEntity;
import io.renren.modules.demo.dto.NewsDTO;

import java.util.List;
import java.util.Map;

/**
 * 文章管理
 *
 * @author WEI 
 * @since 3.0 2022-08-17
 */
public interface AppArticlesService extends CrudService<AppArticlesEntity, AppArticlesDTO> {


    List<AppArticlesDTO> getArticlesList(String type);

    PageData<AppArticlesDTO> page(Map<String, Object> params);
}