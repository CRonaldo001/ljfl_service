package io.renren.modules.AppUserCode.service;

import io.renren.common.service.CrudService;
import io.renren.modules.AppUserCode.dto.AppUserCodeDTO;
import io.renren.modules.AppUserCode.entity.AppUserCodeEntity;

import java.util.List;

/**
 * 用户验证码
 *
 * @author WEI 
 * @since 3.0 2022-08-23
 */
public interface AppUserCodeService extends CrudService<AppUserCodeEntity, AppUserCodeDTO> {
    void sendcode(String phone);

    List<AppUserCodeDTO> getCode(String phone, String code);
}