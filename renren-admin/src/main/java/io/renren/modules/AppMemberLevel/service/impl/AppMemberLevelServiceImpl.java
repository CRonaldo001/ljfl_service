package io.renren.modules.AppMemberLevel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.Result;
import io.renren.modules.AppMemberLevel.dao.AppMemberLevelDao;
import io.renren.modules.AppMemberLevel.dto.AppMemberLevelDTO;
import io.renren.modules.AppMemberLevel.entity.AppMemberLevelEntity;
import io.renren.modules.AppMemberLevel.service.AppMemberLevelService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 积分等级
 *
 * @author cxy
 * @since 3.0 2022-08-26
 */
@Service
public class AppMemberLevelServiceImpl extends CrudServiceImpl<AppMemberLevelDao, AppMemberLevelEntity, AppMemberLevelDTO> implements AppMemberLevelService {

    @Override
    public QueryWrapper<AppMemberLevelEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<AppMemberLevelEntity> wrapper = new QueryWrapper<>();
//        wrapper.eq("tenant_code", TenantContext.getTenantCode(SecurityUser.getUser()));
        return wrapper;
    }


    @Override
    public Result updateLevelInfo(AppMemberLevelDTO dto) {
        List<AppMemberLevelDTO> list = dto.getList();
        int up = 0;
        for (int i = 0; i < list.size(); i++) {
            AppMemberLevelDTO appMemberLevelDTO = list.get(i);
            int low = appMemberLevelDTO.getIntegralLower();
            if (low < up) {
                return new Result().error("上一等级的积分上限需小于下一等级积分下限");
            }
            if (null!=appMemberLevelDTO.getIntegralUpper()) { //最后一个不判断
                up = appMemberLevelDTO.getIntegralUpper();
                if (low > up) {
                    return new Result().error("等级的积分上限需大于等级积分下限");
                }
            }
        }
        for (AppMemberLevelDTO appMemberLevelDTO: list) {
            this.update(appMemberLevelDTO);
        }
        return new Result();
    }

    @Override
    public AppMemberLevelDTO getLevelByIntegral(int integral) {
        Map<String, Object> params = new HashMap<>();
        AppMemberLevelDTO dto=new AppMemberLevelDTO();
        List<AppMemberLevelDTO> list = this.list(params);
        for (AppMemberLevelDTO appMemberLevelDTO : list) {
            int low = appMemberLevelDTO.getIntegralLower();
            if(null==appMemberLevelDTO.getIntegralUpper()){
                if(integral>= low){
                    dto= appMemberLevelDTO;
                }
            }else {
                int up = appMemberLevelDTO.getIntegralUpper();
                if(integral>= low && up>= integral){
                    dto= appMemberLevelDTO;
                }
            }
        }
        return dto;
    }
}