package io.renren.modules.AppUser.service;

import io.renren.common.page.PageData;
import io.renren.common.service.CrudService;
import io.renren.modules.AppGoods.dto.AppGoodsDTO;
import io.renren.modules.AppUser.dto.AppUserDTO;
import io.renren.modules.AppUser.entity.AppUserEntity;


import java.util.List;
import java.util.Map;

/**
 * APP用户
 *
 * @author WEI 
 * @since 3.0 2022-08-22
 */
public interface AppUserService extends CrudService<AppUserEntity, AppUserDTO> {

    List<AppUserDTO> getUserByaNum(String phone, String status);


    PageData<AppUserDTO> page(Map<String, Object> params);

}