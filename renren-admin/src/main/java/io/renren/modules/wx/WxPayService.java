package io.renren.modules.wx;

import com.alibaba.fastjson.JSONObject;

public interface WxPayService {
    /**
     * @return
     * @Description: 微信支付统一下单
     * @Author:
     * @Date: 2019/8/1
     */
    JSONObject unifiedOrder(String receivedName, String phone, String sendType, String addr, Long userId, Long goodsId, Double money, int totalNamorl,int totalSpecial, int goodsSum) throws Exception;


    /**
     * @param orderNo:      订单编号
     * @param amount:       实际支付金额
     * @param refundReason: 退款原因
     * @return
     * @Description: 退款
     * @Author: XCK
     * @Date: 2019/8/6
     */
    ResultMap refund(String orderNo, double amount, String refundReason);

}
