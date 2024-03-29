package io.renren.modules.wx;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Api(tags = "微信支付接口管理")
@RestController
@RequestMapping("/wxPay")
public class WxPayController{
    @Autowired
    private WxPayService wxPayService;
//    /**
//     * 统一下单接口
//     */
//    @ApiOperation(value = "统一下单", notes = "统一下单")
//    @GetMapping("/unifiedOrder")
//    public ResultMap unifiedOrder(
//            @ApiParam(value = "用户id") @RequestParam Long userId,
//            @ApiParam(value = "商品id") @RequestParam Long goodsId,
//            @ApiParam(value = "所需金额") @RequestParam double money,
//            @ApiParam(value = "所需积分") @RequestParam int scores,
//            @ApiParam(value = "商品数量") @RequestParam int goodsSum,
//            HttpServletRequest request) {
//        try {
//            // 1、验证订单是否存在
//            // 2、开始微信支付统一下单
//            ResultMap resultMap = ResultMap.ok(wxPayService.unifiedOrder(userId,goodsId, money, scores,goodsSum));
//            return resultMap;//
//        } catch (Exception e) {
//            return ResultMap.error("运行异常，请联系管理员");
//        }
//    }

    /**
     * 微信支付异步通知
     */
//    @RequestMapping(value = "/notify")
//    public String payNotify(HttpServletRequest request) {
//        InputStream is = null;
//        String xmlBack = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
//        try {
//            is = request.getInputStream();
//            // 将InputStream转换成String
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            xmlBack = wxPayService.notify(sb.toString());
//        } catch (Exception e) {
//          //  logger.error("微信手机支付回调通知失败：", e);
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return xmlBack;
//    }

    @ApiOperation(value = "退款", notes = "退款")
    @PostMapping("/refund")
    public ResultMap refund(@ApiParam(value = "订单号") @RequestParam String orderNo,
                            @ApiParam(value = "退款金额") @RequestParam double amount,
                            @ApiParam(value = "退款原因") @RequestParam(required = false) String refundReason){

        return wxPayService.refund(orderNo, amount, refundReason);
    }

}