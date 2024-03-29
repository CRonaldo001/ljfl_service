package io.renren.modules.AppGoods.controller;

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
import io.renren.modules.AppGoods.dto.AppGoodsDTO;
import io.renren.modules.AppGoods.excel.AppGoodsExcel;
import io.renren.modules.AppGoods.service.AppGoodsService;
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
import java.util.List;
import java.util.Map;


/**
* 商品管理
*
* @author WEI 
* @since 3.0 2022-08-13
*/
@RestController
@RequestMapping("AppGoods/appgoods")
@Api(tags="商品管理")
public class AppGoodsController {
    @Autowired
    private AppGoodsService appGoodsService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("AppGoods:appgoods:page")
    public Result<PageData<AppGoodsDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<AppGoodsDTO> page = appGoodsService.page(params);

        return new Result<PageData<AppGoodsDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("AppGoods:appgoods:info")
    public Result<AppGoodsDTO> get(@PathVariable("id") Long id){
        AppGoodsDTO data = appGoodsService.get(id);

        return new Result<AppGoodsDTO>().ok(data);
    }

//
//    @PostMapping("getGoodsList")
//    @ApiOperation("获取商品列表")
//    public Result<List<AppGoodsDTO>> getGoodsList() {
//        List<AppGoodsDTO> data = appGoodsService.getGoodsList();
//
//        return new Result<List<AppGoodsDTO>>().ok(data);
//    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("AppGoods:appgoods:save")
    public Result save(@RequestBody AppGoodsDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        dto.setSaleCount(0);
        appGoodsService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("AppGoods:appgoods:update")
    public Result update(@RequestBody AppGoodsDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        dto.setUpdateDate(new Date());
        appGoodsService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("AppGoods:appgoods:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        appGoodsService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("AppGoods:appgoods:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<AppGoodsDTO> list = appGoodsService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "商品管理", list, AppGoodsExcel.class);
    }

}