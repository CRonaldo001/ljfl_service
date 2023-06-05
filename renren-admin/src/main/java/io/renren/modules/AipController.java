package io.renren.modules;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import io.lettuce.core.dynamic.annotation.Param;
import io.renren.common.utils.APPResult;
import io.renren.common.utils.Result;
import io.renren.common.utils.StringUtil;
import io.renren.modules.AppArticles.dto.AppArticlesDTO;
import io.renren.modules.AppArticles.service.AppArticlesService;
import io.renren.modules.AppGoods.dto.AppGoodsDTO;
import io.renren.modules.AppGoods.service.AppGoodsService;
import io.renren.modules.AppMemberLevel.dto.AppMemberLevelDTO;
import io.renren.modules.AppMemberLevel.service.AppMemberLevelService;
import io.renren.modules.AppOrder.dto.AppOrderDTO;
import io.renren.modules.AppOrder.service.AppOrderService;
import io.renren.modules.AppSchool.dto.AppSchoolDTO;
import io.renren.modules.AppSchool.service.AppSchoolService;
import io.renren.modules.AppSchoolBuilding.dto.AppSchoolBuildingDTO;
import io.renren.modules.AppSchoolBuilding.service.AppSchoolBuildingService;
import io.renren.modules.AppSchoolCollege.dto.AppSchoolCollegeDTO;
import io.renren.modules.AppSchoolCollege.service.AppSchoolCollegeService;
import io.renren.modules.AppScores.dto.*;
import io.renren.modules.AppScores.service.AppScoresService;
import io.renren.modules.AppSign.dto.AppSignDTO;
import io.renren.modules.AppSign.service.AppSignService;
import io.renren.modules.AppThrows.dto.AppThrowsDTO;
import io.renren.modules.AppThrows.service.AppThrowsService;
import io.renren.modules.AppType.dto.AppTypeDTO;
import io.renren.modules.AppType.service.AppTypeService;
import io.renren.modules.AppUser.dto.AppUserDTO;
import io.renren.modules.AppUser.service.AppUserService;
import io.renren.modules.AppUserCode.dto.AppUserCodeDTO;
import io.renren.modules.AppUserCode.service.AppUserCodeService;
import io.renren.modules.AppVersion.dto.AppVersionDTO;
import io.renren.modules.AppVersion.service.AppVersionService;
import io.renren.modules.common.constant.KxConstants;
import io.renren.modules.security.password.PasswordUtils;
import io.renren.modules.security.service.SysUserTokenService;
//import io.renren.modules.wx.WxPayService;
import io.renren.modules.wx.JSONWXUtil;
import io.renren.modules.wx.ResultMap;
import io.renren.modules.wx.WxPayService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class AipController {

    @Autowired
    private AppGoodsService appGoodsService;
    @Autowired
    private AppScoresService appScoresService;
    @Autowired
    private AppOrderService appOrderService;
    @Autowired
    private AppArticlesService appArticlesService;
    @Autowired
    private AppUserCodeService appUserCodeService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private AppSignService appSignService;
    @Autowired
    private AppMemberLevelService appMemberLevelService;
    @Autowired
    private AppThrowsService appThrowsService;
    @Autowired
    private AppTypeService appTypeService;
    @Autowired
    private AppVersionService appVersionService;
    @Autowired
    private AppSchoolService appSchoolService;
    @Autowired
    private AppSchoolCollegeService appSchoolCollegeService;
    @Autowired
    private AppSchoolBuildingService appSchoolBuildingService;

    @Autowired
    private WxPayService wxPayService;


    @PostMapping("getGoodsList")
    @ApiOperation("获取商品列表")
    public Result<List<AppGoodsDTO>> getGoodsList(@RequestParam("type") String type, String goodsName, @RequestParam("goodsType") String goodsType) {
        if ("all".equals(goodsType)) {
            goodsType = "";
        }

        List<AppGoodsDTO> data = appGoodsService.getGoodsList(type, goodsName, goodsType);
        return new Result<List<AppGoodsDTO>>().ok(data);
    }

    @PostMapping("getGoodsInfo")
    @ApiOperation("获取商品详情")
    public Result<AppGoodsDTO> getGoodsInfo(@RequestBody IdDTO dto) {
        AppGoodsDTO appGoodsDTO = appGoodsService.get(dto.getId());

        return new Result<AppGoodsDTO>().ok(appGoodsDTO);
    }


    @ApiOperation(value = "统一下单", notes = "统一下单")
    @GetMapping("/unifiedOrder")
    public Result<com.alibaba.fastjson.JSONObject> unifiedOrder(
            @ApiParam(value = "收货人") @RequestParam String receivedName,
            @ApiParam(value = "联系电话") @RequestParam String phone,
            @ApiParam(value = "提货方式") @RequestParam String sendType,
            @ApiParam(value = "地址") @RequestParam String addr,
            @ApiParam(value = "用户id") @RequestParam Long userId,
            @ApiParam(value = "商品id") @RequestParam Long goodsId,
            @ApiParam(value = "所需金额") @RequestParam Double money,
            @ApiParam(value = "总环保积分") @RequestParam int totalNamorl,
            @ApiParam(value = "总实践积分") @RequestParam int totalSpecial,
            @ApiParam(value = "商品数量") @RequestParam int goodsSum,
            HttpServletRequest request) {
        try {
            // 1、验证订单是否存在
            // 2、开始微信支付统一下单
            com.alibaba.fastjson.JSONObject resultMap = wxPayService.unifiedOrder(receivedName, phone, sendType, addr, userId, goodsId, money, totalNamorl, totalSpecial, goodsSum);
            if (resultMap != null) {
                return new Result<com.alibaba.fastjson.JSONObject>().ok(resultMap);
            }
        } catch (Exception e) {
            return new Result<com.alibaba.fastjson.JSONObject>().error("运行异常，请联系管理员");
        }
        return new Result<com.alibaba.fastjson.JSONObject>().error("运行异常，请联系管理员");
    }


    /**
     * 微信支付异步通知
     */
    @RequestMapping(value = "/notify")
    public String payNotify(HttpServletRequest request) throws Exception {
        InputStream is = null;
        is = request.getInputStream();
        // 将InputStream转换成String
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        //将数据转为map
        Map<String, Map<String, String>> strToMap = JSONWXUtil.jsonStrToMap(sb.toString());
        //获取微信返回的resource信息
        String associatedData = strToMap.get("resource").get("associated_data");
        String nonce = strToMap.get("resource").get("nonce");
        String ciphertext = strToMap.get("resource").get("ciphertext");
        //使用微信SDK提供的AesUtil工具类和APIv3密钥进行签名验证
        String apiV3Key = "ljfl1234567890123456789012345678";
        AesUtil aesUtil = new AesUtil(apiV3Key.getBytes(StandardCharsets.UTF_8));
        //如果APIv3密钥和微信返回的resource中的信息不一致就会导致签名失败,用户是拿不到微信返回的支付信息的
        String decryptToString = aesUtil.decryptToString(associatedData.getBytes(StandardCharsets.UTF_8),
                nonce.getBytes(StandardCharsets.UTF_8), ciphertext);
        //验证成功后将获取的支付信息转为map
        Map<String, Object> map = JSONWXUtil.strToMap(decryptToString);
        //判断是否支付成功
        if ("SUCCESS".equals(map.get("trade_state"))) {
            //支付成功后进入业务处理逻辑中,这个代码就不贴出来了，每个公司的业务需求都不相同
            // wxPayService.wxCallBack(map);
            List<AppOrderDTO> list = appOrderService.getOrderByNO((String) map.get("out_trade_no"));
            if (list != null && list.size() > 0) {
                AppOrderDTO appOrderDTO = list.get(0);
                appOrderDTO.setStatus(KxConstants.JF_WDH);
                appOrderService.update(appOrderDTO);
                // 添加积分记录
                AppScoresDTO appScoresDTO = new AppScoresDTO();
                appScoresDTO.setUserId(appOrderDTO.getUserId());
                appScoresDTO.setSpecialScore(-appOrderDTO.getTotalSpecial());
                appScoresDTO.setNormalScore(-appOrderDTO.getTotalNamorl()); //减少
                appScoresDTO.setType("GM");// 兑换
                appScoresDTO.setComment("商品购买");
                appScoresDTO.setUrl(appOrderDTO.getUrl());
                appScoresService.save(appScoresDTO);


            }
            //支付成功 给微信发送我已接收通知的响应
            //创建给微信响应的对象
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("code", "200");
            returnMap.put("message", "成功");
            //将返回给微信的响应对象转为xml
            String returnXml = WXPayUtil.mapToXml(returnMap);
            return returnXml;
        }

        //支付失败 给微信发送我已接收通知的响应
        //创建给微信响应的对象
        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("code", "FAIL");
        returnMap.put("message", "失败");
        //将返回给微信的响应对象转为xml
        String returnXml = WXPayUtil.mapToXml(returnMap);
        return returnXml;
    }


    @PostMapping("getTypeInfo")
    @ApiOperation("种类介绍")
    public Result<Map<String, String>> getTypeInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("all", "全部");
        map.put("cosmetics", "美妆");
        map.put("fruit", "水果");
        map.put("snacks", "零食");
        map.put("drink", "饮品");
        map.put("eat", "吃喝");
        map.put("play", "玩乐");
        map.put("numerical", "数码");
        map.put("daily", "日常");
        map.put("other", "其他");

        return new Result<Map<String, String>>().ok(map);
    }


    @PostMapping("getOrderList")
    @ApiOperation("获取订单列表")
    public Result<List<AppOrderDTO>> getOrderList(@RequestBody IdDTO dto) {
        List<AppOrderDTO> data = appOrderService.getOrderList(dto.getId());
        return new Result<List<AppOrderDTO>>().ok(data);
    }

    @PostMapping("getScoreList")
    @ApiOperation("获取积分列表")
    public Result<List<AppScoresDTO>> getScoreList(@RequestBody IdDTO dto) {
        List<AppScoresDTO> data = appScoresService.getScoreList(dto.getId());
        return new Result<List<AppScoresDTO>>().ok(data);
    }


    @PostMapping("getVersion")
    @ApiOperation("获取版本")
    public Result<AppVersionDTO> getVersion(@RequestBody VersionDTO dto) {
        List<AppVersionDTO> list = appVersionService.getList(dto.getPaltform());
        if (!list.isEmpty()) {
            return new Result<AppVersionDTO>().ok(list.get(0));
        }
        return new Result<AppVersionDTO>().ok(null);
    }


    @PostMapping("getMaxScore")
    @ApiOperation("获取最高积分")
    public Result<Map> getMaxScore(@RequestBody IdDTO dto) {
        Map map = new HashMap<>();
        ScoresDTO data = appScoresService.getMaxScores(dto.getId());
        AppMemberLevelDTO levelByIntegral = appMemberLevelService.getLevelByIntegral(data.getNormalScore());
        if (levelByIntegral != null) {
            map.put("level", levelByIntegral.getLevel());

            map.put("charge", levelByIntegral.getServiceCharge() * 100);
        }
        map.put("normalScore", data.getNormalScore());
        map.put("specialScore", data.getSpecialScore());
        return new Result<Map>().ok(map);
    }


    @PostMapping("getArticlesList")
    @ApiOperation("获取文章列表")
    public Result<List<AppArticlesDTO>> getArticlesList(@RequestBody TypeDTO dto) {
        List<AppArticlesDTO> data = appArticlesService.getArticlesList(dto.getType());
        return new Result<List<AppArticlesDTO>>().ok(data);
    }


    @PostMapping("submitGift")
    @ApiOperation("赠送积分")
    public Result<String> submitGift(@RequestBody GiftDTO dto) {
        List<AppUserDTO> userByaNum = appUserService.getUserByaNum(dto.getOtherPhone(), "t");
        if (userByaNum == null || userByaNum.isEmpty()) {
            return new Result().error("对方号码不存在，请检查！");
        }


        List<AppUserDTO> userDTOS = appUserService.getUserByaNum(dto.getPhone(), "t");
        if (userDTOS != null && userDTOS.size() > 0) {
            AppUserDTO appUserDTO = userDTOS.get(0);
            ScoresDTO data = appScoresService.getScores(appUserDTO.getId());


            if (data.getNormalScore() < dto.getScores()) {
                return new Result().error("当前积分不够！");
            }
            Date d = new Date();
            AppScoresDTO other = new AppScoresDTO();
            other.setUrl("/static/images/gift.png");
            other.setNormalScore((int) Math.floor(dto.getScores() * dto.getCharge() / 100));
            other.setSpecialScore(0);
            other.setCreateDate(d);
            other.setComment("收到" + appUserDTO.getNickName() + "的积分");
            other.setUserId(userByaNum.get(0).getId());
            appScoresService.save(other);

            AppScoresDTO user = new AppScoresDTO();
            user.setUrl("/static/images/gift.png");
            user.setNormalScore(-dto.getScores());
            user.setSpecialScore(0);
            user.setCreateDate(d);
            user.setComment("赠送" + userByaNum.get(0).getNickName() + "积分");
            user.setUserId(appUserDTO.getId());
            appScoresService.save(user);

        }


        return new Result<String>().ok("操作完成");
    }


    @PostMapping("getArticleDetail")
    @ApiOperation("获取文章详情")
    public Result<AppArticlesDTO> getArticleDetail(@RequestBody ReadScoresDTO dto) {
        AppArticlesDTO appArticlesDTO = appArticlesService.get(dto.getId());
        if (appArticlesDTO != null) {
            List<AppScoresDTO> readList = appScoresService.getReadList(dto.getUserId(), appArticlesDTO.getType());
            if (readList == null || readList.size() < 5) {
                AppScoresDTO appScoresDTO = new AppScoresDTO();
                appScoresDTO.setNormalScore(1);
                appScoresDTO.setSpecialScore(0);
                appScoresDTO.setUserId(dto.getUserId());
                appScoresDTO.setCreateDate(new Date());
                appScoresDTO.setType(appArticlesDTO.getType());
                appScoresDTO.setComment("每日阅读奖励");
                appScoresDTO.setUrl("/static/images/read.png");
                appScoresService.save(appScoresDTO);
            }
        }
        return new Result<AppArticlesDTO>().ok(appArticlesDTO);
    }


    @PostMapping("getScores")
    @ApiOperation("获取用户积分-环保积分和实践积分")
    public Result<ScoresDTO> getScores(@RequestBody IdDTO dto) {
        ScoresDTO data = appScoresService.getScores(dto.getId());


        return new Result<ScoresDTO>().ok(data);
    }


    @PostMapping("getSign")
    @ApiOperation("获取用户当日签到情况")
    public Result<Map<String, Object>> getSign(@RequestBody IdDTO dto) {
        Map<String, Object> map = new HashMap<>();
        List<AppSignDTO> data = appSignService.getSign(dto.getId());
        if (data.size() > 0) {
            map.put("status", "YQD");
        } else {
            map.put("status", "WQD");
            // 保存今日的签到信息
            AppSignDTO appSignDTO = new AppSignDTO();
            appSignDTO.setUserId(dto.getId());
            appSignDTO.setCreateDate(new Date());
            appSignService.save(appSignDTO);

            //添加积分记录
            AppScoresDTO appScoresDTO = new AppScoresDTO();
            appScoresDTO.setNormalScore(5);
            appScoresDTO.setComment("每日签到奖励");
            appScoresDTO.setUserId(dto.getId());
            appScoresDTO.setType("SIGN");//签到
            appScoresDTO.setUrl("/static/images/sign.png");
            List<String> last7Sign = appSignService.getLast7Sign(dto.getId());

            if (last7Sign != null && last7Sign.size() > 0) {


                if (last7Sign.size() == 7) {
                    appSignService.updateInfo(dto.getId());
                    appScoresDTO.setNormalScore(25);

                }
                List<Integer> ints = new ArrayList<>();
                int y = -1;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                for (int i = 6; i >= 0; i--) {
                    Date date = DateUtils.addDays(new Date(), -i);
                    String formatDate = sdf.format(date);
                    if (last7Sign.contains(formatDate)) {
                        y++;
                        ints.add(y);
                    } else {
                        if (y > -1) {
                            y++;
                        }
                    }

                }
                appScoresService.save(appScoresDTO);//保存积分记录

                int i = caculateTotalTime(last7Sign.get(0), last7Sign.get(last7Sign.size() - 1));
                map.put("days", ints);
                map.put("n", i + 1);
                map.put("score", appScoresDTO.getNormalScore());
            }


        }

        return new Result<Map<String, Object>>().ok(map);
    }


    @PostMapping("addSign")
    @ApiOperation("当日签到")
    public Result<List<AppSignDTO>> addSign(@RequestBody IdDTO dto) {
        AppSignDTO appSignDTO = new AppSignDTO();
        appSignDTO.setUserId(dto.getId());
        appSignDTO.setCreateDate(new Date());
        appSignService.save(appSignDTO);
        List<AppSignDTO> list = new ArrayList<>();
        return new Result<List<AppSignDTO>>().ok(list);
    }

    @PostMapping("addScoresRecord")
    @ApiOperation("产生积分记录")
    public Result<String> addScoresRecord(@RequestBody SaveSocresDTO dto) {
        return appScoresService.addScoresRecord(dto);
    }

    @PostMapping("getQR")
    @ApiOperation("生成二维码")
    public Result<String> getQR(@RequestBody PhoneDTO dto) {
        String path = "C:\\Users\\Administrator\\Desktop\\nginx-1.22.1\\html\\fileUpload\\" + dto.getPhone() + ".png";
        File file = new File("path");
        if (!file.exists()) {
            QrCodeUtil.generate(dto.getPhone() + "", 300, 300, FileUtil.file(path));
        }

        return new Result<String>().ok("http://1.14.63.104:80/fileUpload/" + dto.getPhone() + ".png");

    }

    @PostMapping("userRegister")
    @ApiOperation("用户注册")
    public Result userRegister(@RequestBody AppUserDTO dto) {


        List<AppUserCodeDTO> code = appUserCodeService.getCode(dto.getPhoneNum(), dto.getCode());
        dto.setStatus("t");
        boolean flag = true;
        if (code != null && code.size() > 0) {
            Date date = new Date();
            if (date.before(code.get(0).getExpiredDate())) {
                String newPassword = PasswordUtils.encode(dto.getPassword());
                dto.setPassword(newPassword);
            } else {
                return new Result().error("验证码已过期，请重新获取...");
            }
        } else {
            return new Result().error("验证码错误，请重新填写...");
        }
        List<AppUserDTO> userByaNum = appUserService.getUserByaNum(dto.getPhoneNum(), "");


        if (dto.getSchoolId() != null) {
            AppSchoolDTO appSchoolDTO = appSchoolService.get(dto.getSchoolId());
            if (appSchoolDTO != null) {
                dto.setSchoolName(appSchoolDTO.getSchoolName());
            }
        }
        if (dto.getBuildId() != null) {
            AppSchoolBuildingDTO buildingDTO = appSchoolBuildingService.get(dto.getBuildId());
            if (buildingDTO != null) {
                dto.setBuildName(buildingDTO.getBuildingName());
            }
        }
        if (dto.getCollegeId() != null) {
            AppSchoolCollegeDTO collegeDTO = appSchoolCollegeService.get(dto.getCollegeId());
            if (collegeDTO != null) {
                dto.setCollegeName(collegeDTO.getCollegeName());
            }
        }
        if (userByaNum != null && userByaNum.size() > 0) {
            flag = false;
            if ("t".equals(userByaNum.get(0).getStatus())) {
                return new Result().error("该电话已注册，请登录...");
            } else {
                Long id = userByaNum.get(0).getId();
                dto.setId(id);
                appUserService.update(dto);
            }
        } else {
            appUserService.save(dto);
        }
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("nickname", dto.getNickName());
        userMap.put("professional", dto.getProfessional());
        userMap.put("schoolName", dto.getSchoolName());
        userMap.put("room", dto.getRoom());
        userMap.put("schoolId", dto.getSchoolId());
        userMap.put("buildId", dto.getBuildId());
        userMap.put("buildName", dto.getBuildName());
        userMap.put("collegeId", dto.getCollegeId());
        userMap.put("collegeName", dto.getCollegeName());
        userMap.put("sex", dto.getSex());
        userMap.put("classes", dto.getClasses());
        userMap.put("studentNum", dto.getStudentNum());
        userMap.put("phone", dto.getPhoneNum());
        userMap.put("type", dto.getType());
        userMap.put("uid", dto.getId());

        Result token = sysUserTokenService.createToken(dto.getId());
        HashMap data = (HashMap) token.getData();
        Map<String, Object> map = new HashMap<>(2);
        map.put("token", data.get("token"));
        map.put("tokenExpired", data.get("tokenExpired"));
        map.put("uid", dto.getId());
        map.put("userInfo", userMap);
        map.put("code", 0);
        Date d = new Date();

        if (flag) {
            try {
                AppScoresDTO other = new AppScoresDTO();
                other.setUrl("/static/images/newUser.png");
                other.setNormalScore(100);//赠送100积分
                other.setSpecialScore(0);
                other.setCreateDate(d);
                other.setComment("新人福利");
                other.setUserId(dto.getId());
                appScoresService.save(other);
                List<AppUserDTO> inviters = appUserService.getUserByaNum(dto.getInviteNum(), "");
                if (inviters != null && inviters.size() > 0) {
                    AppScoresDTO inviter = new AppScoresDTO();
                    inviter.setUrl("/static/images/newUser.png");
                    inviter.setNormalScore(100);//赠送100积分
                    inviter.setSpecialScore(0);
                    inviter.setCreateDate(d);
                    inviter.setComment("推荐奖励 " + dto.getPhoneNum());
                    inviter.setUserId(inviters.get(0).getId());
                    appScoresService.save(inviter);
                }

            } catch (Exception e) {

            }
        }


        return new Result().ok(map);

    }

    @PostMapping("delUser")
    @ApiOperation("用户注销")
    public Result delUser(@RequestBody PhoneDTO dto) {
        List<AppUserDTO> userByaNum = appUserService.getUserByaNum(dto.getPhone(), "");
        if (userByaNum != null && userByaNum.size() > 0) {
            // appUserService.deleteById(userByaNum.get(0).getId());
            userByaNum.get(0).setStatus("f");
            appUserService.update(userByaNum.get(0));
        }
        return new Result();
    }


    @PostMapping("sendCode")
    @ApiOperation("发送验证码")
    public Result sendCode(@RequestBody PhoneDTO dto) {
        List<AppUserDTO> userByaNum = appUserService.getUserByaNum(dto.getPhone(), "t");
        if (userByaNum != null && userByaNum.size() > 1) {
            return new Result().error();
        }
        appUserCodeService.sendcode(dto.getPhone());
        return new Result();

    }


    @PostMapping("sendCode2")
    @ApiOperation("重置密码 发送验证码")
    public Result sendCode2(@RequestBody PhoneDTO dto) {
        List<AppUserDTO> userByaNum = appUserService.getUserByaNum(dto.getPhone(), "t");
        if (userByaNum == null || userByaNum.isEmpty()) {
            return new Result().error();
        }
        appUserCodeService.sendcode(dto.getPhone());
        return new Result();
    }


    @PostMapping("getTimes")
    @ApiOperation("获取垃圾投递次数")
    public Result<Map> getTimes(@RequestBody PhoneDTO dto) {

        Map map = new HashMap<>();
        map.put("LJ1", 0);
        map.put("LJ3", 0);
        map.put("LJ5", 0);

        List<AppUserDTO> userByaNum = appUserService.getUserByaNum(dto.getPhone(), "");
        if (userByaNum != null || userByaNum.size() > 0) {
            List<AppScoresDTO> listBytype1 = appScoresService.getListBytype(userByaNum.get(0).getId(), "LJ-1");
            List<AppScoresDTO> listBytype3 = appScoresService.getListBytype(userByaNum.get(0).getId(), "LJ-3");
            List<AppScoresDTO> listBytype5 = appScoresService.getListBytype(userByaNum.get(0).getId(), "LJ-2");

            if (!listBytype1.isEmpty()) {
                map.put("LJ1", listBytype1.size());

            }
            if (!listBytype3.isEmpty()) {
                map.put("LJ3", listBytype3.size());
            }
            if (!listBytype5.isEmpty()) {
                map.put("LJ5", listBytype5.size());
            }
        }
        return new Result().ok(map);
    }


    @PostMapping("fenlei/fl/trash/pushRecord")
    @ApiOperation("投递记录")
    public APPResult<Map> pushRecord(@RequestParam("id") String id, @RequestParam("swipeNo") String swipeNo, @RequestParam("garbageTypeCode") String garbageTypeCode, @RequestParam("weight") Double weight, @RequestParam("deviceNo") int deviceNo) {

        AppThrowsDTO appThrowsDTO = new AppThrowsDTO();
        appThrowsDTO.setDeviceNo(deviceNo);
        appThrowsDTO.setSwipeNo(swipeNo);
        appThrowsDTO.setGarbageTypeCode(garbageTypeCode);
        appThrowsDTO.setWeight(weight);
        appThrowsDTO.setCreateDate(new Date());
        appThrowsDTO.setDeviceId(id);
        appThrowsService.save(appThrowsDTO);

        List<AppTypeDTO> list = appTypeService.list(null);
        int rat = 8;
        String type = "其他";
        //int code = 2;
//        if (list != null && list.size() > 0) {
//            for (AppTypeDTO typeDTO : list) {
//                if (typeDTO.getType() == Integer.parseInt(garbageTypeCode)) {
//                    rat = typeDTO.getScores();
//                    type = typeDTO.getRemark();
//                    code = typeDTO.getType();
//                    break;
//                }
//            }
//        }
        Map map = new HashMap();
        map.put("integralBalance", 0);
        List<AppUserDTO> userByaNum = appUserService.getUserByaNum(swipeNo, "");
        if (userByaNum != null && userByaNum.size() > 0) {
            double v = appThrowsDTO.getWeight() * 1000 / 100;
            //添加积分记录
            AppScoresDTO appScoresDTO = new AppScoresDTO();
            appScoresDTO.setSpecialScore((int) (rat * v));
            appScoresDTO.setComment("垃圾回收" + type);
            appScoresDTO.setUserId(userByaNum.get(0).getId());
            appScoresDTO.setType("LJ-" + Integer.parseInt(garbageTypeCode));//0
            appScoresDTO.setUrl("/static/images/sign.png");
            appScoresService.save(appScoresDTO);

            map.put("integralBalance", appScoresDTO.getSpecialScore());
        }


        return new APPResult().ok(map);
    }


    @GetMapping("fenlei/fl/trash/checkCard")
    @ApiOperation("检查号码")
    public APPResult<Map> checkCard(@RequestParam("cardNo") String cardNo) {
        List<AppUserDTO> userByaNum = appUserService.getUserByaNum(cardNo, "t");
        if (userByaNum == null || userByaNum.isEmpty()) {
            return new APPResult().error(20003, "用户不存在");
        }
        ScoresDTO data = appScoresService.getScores(userByaNum.get(0).getId());
        Map map = new HashMap<>();
        map.put("integralBalance", data.getSpecialScore());
        map.put("name", userByaNum.get(0).getNickName());
        map.put("phone", userByaNum.get(0).getPhoneNum());
        map.put("isOpen", 1);

        return new APPResult().ok(map);
    }

    @GetMapping("fenlei/fl/trash/checkRegister")
    @ApiOperation("验证设备是否注册")
    public APPResult<Map> checkRegister(@RequestParam("mid") String mid) {
        Map map = new HashMap();
        map.put("name", "6 号楼 2 单元前（3 组点位中的双厨余）");
        map.put("id", "00000000001");
        map.put("orgName", "02f3ae1ca007410e916e25eb878a542a");
        map.put("qrCode", "无");
        map.put("closeTime", "10");
        map.put("openTime", "5");
        map.put("orgId", "000");
        return new APPResult().ok(map);
    }


    @GetMapping("fenlei/fl/trash/getInfo")
    @ApiOperation("获取设备基础信息")
    public APPResult<Map> getInfo(@RequestParam("deviceId") String deviceId) {
        Map map = new HashMap();


        List<Object> imgs = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.set("isNewRecord", true);
        object.set("src", "1/20190828/44bd3d215672405cb4bd07c4345f2b8f.jpg");

        JSONObject object2 = new JSONObject();
        object2.set("isNewRecord", true);
        object2.set("src", "1/20190801/a868592712f146aa85c6b2adcd62ca76.png");
        imgs.add(object);
        imgs.add(object2);


        JSONObject device = new JSONObject();
        List<Object> binList = new ArrayList<>();
        JSONObject bin1 = new JSONObject();
        bin1.set("no", 0);
        bin1.set("garbageTypeName", "纸张");
        bin1.set("garbageTypeCode", "1");
        bin1.set("colorCode", "tdan_zz");


        JSONObject bin2 = new JSONObject();
        bin2.set("no", 1);
        bin2.set("garbageTypeName", "塑料");
        bin2.set("garbageTypeCode", "3");
        bin2.set("colorCode", "tdan_sl");


        JSONObject bin3 = new JSONObject();
        bin3.set("no", 2);
        bin3.set("garbageTypeName", "其他");
        bin3.set("garbageTypeCode", "2");
        bin3.set("colorCode", "tdan_qt");


        binList.add(bin1);
        binList.add(bin2);
        binList.add(bin3);

        device.set("binList", binList);
        device.set("closeTime", "17:30,20:30");
        device.set("id", "0a44153bb89940daa734b515ea69437a");
        device.set("openTime", "6:30,9:30");
        map.put("imgs", imgs);
        map.put("device", device);
        return new APPResult().ok(map);
    }


    @PostMapping("updateUser")
    @ApiOperation("修改用户")
    public Result<AppUserDTO> updateUser(@RequestBody UpdateUserDTO dto) {

        if (StringUtil.isNotEmpty(dto.getCode())) {

            List<AppUserCodeDTO> code = appUserCodeService.getCode(dto.getPhone(), dto.getCode());
            if (code != null && code.size() > 0) {
                Date date = new Date();
                if (date.after(code.get(0).getExpiredDate())) {
                    return new Result().error("验证码已过期，请重新获取...");
                }
            } else {
                return new Result().error("验证码错误，请重新填写...");
            }
        }


        List<AppUserDTO> userByaNum = appUserService.getUserByaNum(dto.getPhone(), "t");
        if (userByaNum == null || userByaNum.isEmpty()) {
            return new Result().error("用户信息错误！");
        }
        AppUserDTO user = userByaNum.get(0);
        String value = dto.getValue();
        switch (dto.getName()) {
            case "nickname":
                user.setNickName(value);
                break;
            case "type":
                user.setType(value);
                break;
            case "schoolId":
                user.setSchoolId(Long.parseLong(value));

                user.setCollegeName("");
                user.setBuildName("");
                user.setCollegeId(null);
                user.setBuildId(null);
                AppSchoolDTO appSchoolDTO = appSchoolService.get(Long.parseLong(value));
                if (appSchoolDTO != null) {
                    user.setSchoolName(appSchoolDTO.getSchoolName());
                }

                break;
            case "buildId":
                user.setBuildId(Long.parseLong(value));
                AppSchoolBuildingDTO appSchoolBuildingDTO = appSchoolBuildingService.get(Long.parseLong(value));
                if (appSchoolBuildingDTO != null) {
                    user.setBuildName(appSchoolBuildingDTO.getBuildingName());

                }
                break;
            case "collegeId":
                user.setCollegeId(Long.parseLong(value));
                AppSchoolCollegeDTO appSchoolCollegeDTO = appSchoolCollegeService.get(Long.parseLong(value));
                if (appSchoolCollegeDTO != null) {
                    user.setCollegeName(appSchoolCollegeDTO.getCollegeName());
                }
                break;
            case "professional":
                user.setProfessional(value);
                break;
            case "sex":
                user.setSex(value);
                break;
            case "classes":
                user.setClasses(value);
                break;
            case "room":
                user.setRoom(value);
                break;
            case "studentNum":
                user.setStudentNum(value);
                break;
            case "password":
                user.setPassword(PasswordUtils.encode(value));
                break;
            default:
                break;
        }
        appUserService.update(user);
        return new Result<AppUserDTO>().ok(user);

    }


    @PostMapping("login")
    @ApiOperation("登录")
    public Result login(@RequestBody LoginDTO dto) {
        List<AppUserDTO> userByaNum = appUserService.getUserByaNum(dto.getPhone(), "t");
        if (userByaNum != null && userByaNum.size() > 0) {
            if (!PasswordUtils.matches(dto.getPassword(), userByaNum.get(0).getPassword())) {
                return new Result().error("电话号或密码错误！");
            }
        } else {
            return new Result().error("电话号或密码错误！");
        }

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("nickname", userByaNum.get(0).getNickName());
        userMap.put("professional", userByaNum.get(0).getProfessional());
        userMap.put("schoolName", userByaNum.get(0).getSchoolName());
        userMap.put("classes", userByaNum.get(0).getClasses());
        userMap.put("studentNum", userByaNum.get(0).getStudentNum());
        userMap.put("phone", userByaNum.get(0).getPhoneNum());
        userMap.put("type", userByaNum.get(0).getType());
        userMap.put("uid", userByaNum.get(0).getId());
        userMap.put("buildName", userByaNum.get(0).getBuildName());
        userMap.put("room", userByaNum.get(0).getRoom());

        Result token = sysUserTokenService.createToken(userByaNum.get(0).getId());
        HashMap data = (HashMap) token.getData();
        Map<String, Object> map = new HashMap<>(2);
        map.put("token", data.get("token"));
        map.put("tokenExpired", data.get("tokenExpired"));
        map.put("uid", userByaNum.get(0).getId());
        map.put("userInfo", userMap);
        map.put("code", 0);
        return new Result().ok(map);

    }

    public int caculateTotalTime(String startTime, String endTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        Long l = 0L;
        try {
            date1 = formatter.parse(startTime);
            date2 = formatter.parse(endTime);
            l = (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l.intValue();
    }

    @GetMapping("getListSchool")
    public Result<List<AppSchoolDTO>> getListSchool() {
        List<AppSchoolDTO> page = appSchoolService.getListSchool();
        return new Result<List<AppSchoolDTO>>().ok(page);
    }


    @GetMapping("getCollegesBySchoolId")
    public Result<List<AppSchoolCollegeDTO>> getListBySchoolId(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<AppSchoolCollegeDTO> page = appSchoolCollegeService.getListBySchoolId(params);

        return new Result<List<AppSchoolCollegeDTO>>().ok(page);
    }

    @GetMapping("getBuildsBySchoolId")
    public Result<List<AppSchoolBuildingDTO>> getBuildsBySchoolId(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<AppSchoolBuildingDTO> page = appSchoolBuildingService.getListBySchoolId(params);
        return new Result<List<AppSchoolBuildingDTO>>().ok(page);
    }


}
