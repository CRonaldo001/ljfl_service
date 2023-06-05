package io.renren.modules.AppVersion.service;

import io.renren.common.service.CrudService;
import io.renren.modules.AppVersion.dto.AppVersionDTO;
import io.renren.modules.AppVersion.entity.AppVersionEntity;

import java.util.List;

/**
 * app版本
 *
 * @author WEI 
 * @since 3.0 2022-09-20
 */
public interface AppVersionService extends CrudService<AppVersionEntity, AppVersionDTO> {
    List<AppVersionDTO> getList(String paltform);
}