package io.renren.modules.wx;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lpx
 * @date 2021/4/15
 */
@Slf4j
public class JSONWXUtil {

    /**
     * Json字符串转Map
     * @param wxStr：微信返回的数据
     * @return
     */
    public static Map<String, Map<String,String>> jsonStrToMap(String wxStr) {
        Map<String,Map<String,String>> map = (Map) JSON.parse(wxStr);
        log.info("Json字符串转Map：" + map);
        return map;
    }

    /**
     * str转map
     * @param str：字符串
     * @return
     */
    public static Map<String,Object> strToMap(String str){
        Map<String,Object> map = JSON.parseObject(str, HashMap.class);
        log.info("微信回调验证签名后支付信息：" + map);
        return map;
    }
}
