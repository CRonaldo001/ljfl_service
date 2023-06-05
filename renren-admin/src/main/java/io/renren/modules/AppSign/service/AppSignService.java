package io.renren.modules.AppSign.service;

import io.renren.common.service.CrudService;
import io.renren.modules.AppSign.dto.AppSignDTO;
import io.renren.modules.AppSign.entity.AppSignEntity;

import java.util.List;

/**
 * 签到
 *
 * @author WEI 
 * @since 3.0 2022-08-26
 */
public interface AppSignService extends CrudService<AppSignEntity, AppSignDTO> {

    List<AppSignDTO> getSign(Long id);


    List<String> getLast7Sign(Long id);

   void updateInfo(Long id);


}