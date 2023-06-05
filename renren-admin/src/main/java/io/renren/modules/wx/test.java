package io.renren.modules.wx;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;

import java.io.IOException;
import java.util.*;

public class test {
    public static void main(String[] args) throws Exception {

        Date date = new Date();

        long time = date.getTime();

        System.err.println(time/1000);
    }


    public static Map<String, Object> json2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        if(jsonStr != null && !"".equals(jsonStr)){
            //最外层解析
            try {
                Map jsonObject = objectMapper.readValue(jsonStr, Map.class);
                for (Object k : jsonObject.keySet()) {
                    Object v = jsonObject.get(k);
                    //如果内层还是数组的话，继续解析
                    if (v instanceof ArrayList) {
                        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                        Iterator<Map> it = ((ArrayList) v).iterator();
                        while (it.hasNext()) {
                            Map json2 = it.next();
                            list.add(json2Map(objectMapper.writeValueAsString(json2)));
                        }
                        map.put(k.toString(), list);
                    } else {
                        map.put(k.toString(), v);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return map;
        }else{
            return null;
        }
    }




}
