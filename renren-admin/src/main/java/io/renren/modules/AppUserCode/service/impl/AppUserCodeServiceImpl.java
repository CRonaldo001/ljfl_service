package io.renren.modules.AppUserCode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import io.renren.common.context.TenantContext;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.constant.Constant;
import io.renren.modules.AppUserCode.dao.AppUserCodeDao;
import io.renren.modules.AppUserCode.dto.AppUserCodeDTO;
import io.renren.modules.AppUserCode.entity.AppUserCodeEntity;
import io.renren.modules.AppUserCode.service.AppUserCodeService;
import io.renren.modules.security.user.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户验证码
 *
 * @author WEI
 * @since 3.0 2022-08-23
 */
@Service
public class AppUserCodeServiceImpl extends CrudServiceImpl<AppUserCodeDao, AppUserCodeEntity, AppUserCodeDTO> implements AppUserCodeService {

    @Override
    public QueryWrapper<AppUserCodeEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<AppUserCodeEntity> wrapper = new QueryWrapper<>();


        wrapper.eq("tenant_code", TenantContext.getTenantCode(SecurityUser.getUser()));


        return wrapper;
    }


    @Override
    public void sendcode(String phone) {
        this.baseDao.deleteByUserId(phone);
        String code = Math.round((Math.random()+1) * 1000) + "";


        AppUserCodeDTO dto = new AppUserCodeDTO();
        dto.setPhone(phone);
        dto.setCode(code);
        long currentTime = System.currentTimeMillis();
        currentTime += 2 * 60 * 1000;
        Date date = new Date(currentTime);
        dto.setExpiredDate(date);
        System.err.println("code:" + code);

        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential("AKID8FE2eDZgYCoOjzACBoKaaIQIuY3Lakmf", "nNjsnV8YouqmfQ4vhXUUN4YuDMFBgDcr");
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {phone};
            req.setPhoneNumberSet(phoneNumberSet1);

            req.setSmsSdkAppId("1400726875");
            req.setSignName("成都壹点联创科技有限公司");
            req.setTemplateId("1589382");

            String[] templateParamSet1 = {code, "2"};
            req.setTemplateParamSet(templateParamSet1);

            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }


        this.save(dto);
    }

    @Override
    public List<AppUserCodeDTO> getCode(String phone, String code) {
        return this.baseDao.getCode(phone, code);
    }
}