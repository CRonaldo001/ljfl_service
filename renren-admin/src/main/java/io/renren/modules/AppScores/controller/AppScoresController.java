package io.renren.modules.AppScores.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.AppScores.dto.AddScoresDTO;
import io.renren.modules.AppScores.dto.AppScoresDTO;
import io.renren.modules.AppScores.excel.AppScoresExcel;
import io.renren.modules.AppScores.service.AppScoresService;
import io.renren.modules.AppThrows.dto.AppThrowsDTO;
import io.renren.modules.AppThrows.service.AppThrowsService;
import io.renren.modules.AppType.dto.AppTypeDTO;
import io.renren.modules.AppUser.dto.AppUserDTO;
import io.renren.modules.AppUser.service.AppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* 积分记录
*
* @author WEI 
* @since 3.0 2022-08-13
*/
@RestController
@RequestMapping("AppScores/appscores")
@Api(tags="积分记录")
public class AppScoresController {
    @Autowired
    private AppScoresService appScoresService;
    @Autowired
    private AppThrowsService appThrowsService;
    @Autowired
    private AppUserService appUserService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("AppScores:appscores:page")
    public Result<PageData<AppScoresDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<AppScoresDTO> page = appScoresService.page(params);

        return new Result<PageData<AppScoresDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("AppScores:appscores:info")
    public Result<AppScoresDTO> get(@PathVariable("id") Long id){
        AppScoresDTO data = appScoresService.get(id);

        return new Result<AppScoresDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("AppScores:appscores:save")
    public Result save(@RequestBody AppScoresDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        appScoresService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("AppScores:appscores:update")
    public Result update(@RequestBody AppScoresDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        appScoresService.update(dto);

        return new Result();
    }


    @PutMapping("addScoresByUser")
    @ApiOperation("手动录入")
    @LogOperation("手动录入")
    public Result addScoresByUser(@RequestBody AddScoresDTO dto){
        //效验数据
//        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
//
//        appScoresService.update(dto);

        AppThrowsDTO appThrowsDTO = new AppThrowsDTO();
        appThrowsDTO.setDeviceNo(0);
        appThrowsDTO.setSwipeNo(dto.getPhone());
        appThrowsDTO.setGarbageTypeCode(dto.getType());
        appThrowsDTO.setWeight(dto.getWeight());
        appThrowsDTO.setCreateDate(new Date());
        appThrowsDTO.setDeviceId("");
        appThrowsService.save(appThrowsDTO);

//        List<AppTypeDTO> list = appTypeService.list(null);
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
        List<AppUserDTO> userByaNum = appUserService.getUserByaNum(dto.getPhone(),"");
        if (userByaNum != null || userByaNum.size() > 0) {
            double v = appThrowsDTO.getWeight() * 1000 / 100;
            //添加积分记录
            AppScoresDTO appScoresDTO = new AppScoresDTO();
            appScoresDTO.setSpecialScore((int) (rat * v));
            appScoresDTO.setComment("垃圾回收");
            appScoresDTO.setUserId(userByaNum.get(0).getId());
            appScoresDTO.setType("LJ-" + Integer.parseInt(dto.getType()));//0
            appScoresDTO.setUrl("/static/images/sign.png");
            appScoresService.save(appScoresDTO);
            map.put("integralBalance", appScoresDTO.getSpecialScore());
        }

        return new Result();
    }



    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("AppScores:appscores:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        appScoresService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("AppScores:appscores:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<AppScoresDTO> list = appScoresService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "积分记录", list, AppScoresExcel.class);
    }

}