package io.renren.modules.wx;


import com.alibaba.fastjson.JSONObject;
import io.renren.common.utils.StringUtil;
import io.renren.modules.AppGoods.dto.AppGoodsDTO;
import io.renren.modules.AppGoods.service.AppGoodsService;
import io.renren.modules.AppOrder.dto.AppOrderDTO;
import io.renren.modules.AppOrder.service.AppOrderService;
import io.renren.modules.AppScores.dto.AppScoresDTO;
import io.renren.modules.AppScores.service.AppScoresService;
import io.renren.modules.common.constant.KxConstants;
import io.renren.modules.wx2.wxpService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class WxPayServiceImpl implements WxPayService {
    private static final String mchID = "1642739924";
    private static final String noturl = "https://www.ljfl.work/notify";
    private final Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);
    @Autowired
    private WxPayAppConfig wxPayAppConfig;
    @Autowired
    private wxpService wxpService;
    @Autowired
    private AppGoodsService appGoodsService;
    @Autowired
    private AppOrderService appOrderService;
    @Autowired
    private AppScoresService appScoresService;

    @Override
    public JSONObject unifiedOrder(String receivedName, String phone, String sendType, String addr, Long userId, Long goodsId, Double money, int totalNamorl, int totalSpecial, int goodsSum) throws Exception {

        try {
            JSONObject jsonObject = new JSONObject();
            AppGoodsDTO dto = appGoodsService.get(goodsId);
            // 添加兑换记录
            AppOrderDTO appOrderDTO = new AppOrderDTO();
            appOrderDTO.setUserId(userId);
            appOrderDTO.setStatus(KxConstants.JF_WZF);
            appOrderDTO.setGoodsName(dto.getName());
            if (StringUtil.isNotEmpty(dto.getUrl())) {
                String[] split = dto.getUrl().split(";");
                if (split != null) {
                    appOrderDTO.setUrl(split[0]);
                }
            }
            appOrderDTO.setTotalNamorl(totalNamorl);
            appOrderDTO.setTotalSpecial(totalSpecial);
            appOrderDTO.setGoodsCount(goodsSum);
            appOrderDTO.setTransactionId(generateWord());
            appOrderDTO.setCreateDate(new Date());
            appOrderDTO.setReceivedName(receivedName);
            appOrderDTO.setUserPhone(phone);
            appOrderDTO.setSendType(sendType);
            appOrderDTO.setAddr(addr);
            appOrderDTO.setMoney(money);
            String noncestr = "";
            if (money > 0) {
                noncestr = RandomStringUtils.randomAlphanumeric(32).toLowerCase().toUpperCase();
                appOrderDTO.setOutTradeNo(noncestr);
            } else {
                appOrderDTO.setStatus(KxConstants.JF_WDH);
                // 添加积分记录
                AppScoresDTO appScoresDTO = new AppScoresDTO();
                appScoresDTO.setUserId(userId);
                appScoresDTO.setSpecialScore(-totalSpecial);
                appScoresDTO.setNormalScore(-totalNamorl); //减少
                appScoresDTO.setType("DH");// 兑换
                appScoresDTO.setComment("商品兑换");
                if (StringUtil.isNotEmpty(dto.getUrl())) {
                    String[] split = dto.getUrl().split(";");
                    if (split != null) {
                        appScoresDTO.setUrl(split[0]);
                    }
                }
                appScoresDTO.setUrl(dto.getUrl());
                appScoresService.save(appScoresDTO);
            }
            appOrderService.save(appOrderDTO);


            if (money > 0) {
                jsonObject = wxpService.wxPay(noturl, money, noncestr);
                return jsonObject;
            } else {
                jsonObject.put("code", "200");
                return jsonObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    private String generateWord() {
        String[] beforeShuffle = new String[]{"2", "3", "4", "5", "6", "7",
                "8", "9", "0", "1"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        return result;
    }


    @Override
    public ResultMap refund(String orderNo, double amount, String refundReason) {
        return null;
    }


}
