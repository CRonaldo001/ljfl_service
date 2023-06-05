package io.renren.modules.wx2;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class wxpService {
    @Autowired
    private WxPay wxPay;

    public JSONObject wxPay(String noturl, Double money,  String noncestr) {
        //生成32位随机字母加数字 并转换为大写
        JSONObject jsonObject = new JSONObject();

        Double money2 = money * 100;

        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/app");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("appid", wxPay.APP_ID)//appID
                .put("mchid", wxPay.MCH_ID)//商户ID
                .put("description", "商品描述")
                .put("notify_url", noturl)//异步通知接口
                .put("out_trade_no", noncestr);//商户订单ID
        rootNode.putObject("amount")
                .put("total", money2.intValue());//付款金额

        //请求头
        try {
            objectMapper.writeValue(bos, rootNode);
            httpPost.setEntity(new StringEntity(bos.toString("UTF-8"), "UTF-8"));
            CloseableHttpResponse response = wxPay.httpClient().execute(httpPost);
            //请求返回结构
            String bodyAsString = EntityUtils.toString(response.getEntity());
            //将返回结果转换为jsonObject
            jsonObject = JSONObject.parseObject(bodyAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //当返回结果中有prepay_id这个key时 预付订单成功
        Date t = new Date();

        if (jsonObject.containsKey("prepay_id")) {//预订单成功
            //返回结果参数
            jsonObject.put("appid", wxPay.APP_ID);
            jsonObject.put("package", "Sign=WXPay");//固定值
            jsonObject.put("partnerid", wxPay.MCH_ID);//微信支付商户号
            jsonObject.put("timestamp", t.getTime());//时间戳
            jsonObject.put("noncestr", noncestr);
            jsonObject.put("key", wxPay.KEY);
            jsonObject.put("prepayid", jsonObject.get("prepay_id"));
            //因为是基于uni-app编写的app 所以直接在后台进行唤起支付加密
            List<String> list = new ArrayList<>();
            list.add(wxPay.APP_ID);
            list.add(String.valueOf(t.getTime()));
            list.add(noncestr);
            list.add((String) jsonObject.get("prepay_id"));
            jsonObject.put("sign", this.createQian(list));// app唤起签名
            jsonObject.remove("prepay_id");

            return jsonObject;
        }
        return null;
    }

    /**
     * 生成app唤起支付签名
     *
     * @param pram 参数
     *             应用id
     *             时间戳
     *             随机字符串
     *             预支付交易会话ID
     * @return
     */
    public String createQian(List<String> pram) {
        StringBuffer sBuffer = new StringBuffer();
        for (String str : pram) {
            sBuffer.append(str + "\n");
        }
        //使用私匙文件对字符串进行 WECHATPAY2-SHA256-RSA2048
        //对结果进行base64加密
        Signature sign = null;
        byte[] sign1 = null;
        try {
            sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(wxPay.getPRIVATE_KEY());
            sign.update(sBuffer.toString().getBytes("UTF-8"));
            sign1 = sign.sign();
        } catch (UnsupportedEncodingException | SignatureException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(sign1);
    }

    //解密返回参数
    public String decryptToString(byte[] associatedData, byte[] nonce, String ciphertext)
            throws GeneralSecurityException, IOException {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            SecretKeySpec key = new SecretKeySpec(wxPay.KEY.getBytes(), "AES");
            GCMParameterSpec spec = new GCMParameterSpec(128, nonce);

            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData);

            return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)), "utf-8");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
