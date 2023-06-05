package io.renren.modules.wx2;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.ScheduledUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
@Component
public class WxPay {

    public String APP_ID = "wxe0778655a1fcc038";             //应用id
    public String KEY = "ljfl1234567890123456789012345678";                 //apiv3 密码
    public String MCH_ID = "1642739924";              //商户id
    public String merchantSerialNumber = "5CE37A9568E98684D69B0125891370E0985B1264";//证书编号

    private String payPath;                 //证书地址
    private PrivateKey PRIVATE_KEY;
    public String path = "https://api.mch.weixin.qq.com/v3/pay/transactions/app";//app支付统一下单地址
    public String schema = "WECHATPAY2-SHA256-RSA2048";

    @Bean
    public CloseableHttpClient httpClient() throws IOException {
        //读取私匙证书
        PrivateKey private_key = this.getPRIVATE_KEY();
        //定时更新证书
        ScheduledUpdateCertificatesVerifier verifier = new ScheduledUpdateCertificatesVerifier(
                new WechatPay2Credentials(MCH_ID, new PrivateKeySigner(merchantSerialNumber, private_key)),
                KEY.getBytes(StandardCharsets.UTF_8));
        //请求构建器
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(MCH_ID, merchantSerialNumber, PRIVATE_KEY)
                .withValidator(new WechatPay2Validator(verifier));
        //http请求
        return builder.build();
    }

    public PrivateKey getPRIVATE_KEY() {
        try {
            PRIVATE_KEY = PemUtil.loadPrivateKey(new FileInputStream("C:\\cert\\" + "apiclient_key.pem"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this.PRIVATE_KEY;
    }


}
