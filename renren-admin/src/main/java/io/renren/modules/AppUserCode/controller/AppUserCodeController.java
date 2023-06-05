package io.renren.modules.AppUserCode.controller;

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
import io.renren.modules.AppUserCode.dto.AppUserCodeDTO;
import io.renren.modules.AppUserCode.excel.AppUserCodeExcel;
import io.renren.modules.AppUserCode.service.AppUserCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
* 用户验证码
*
* @author WEI 
* @since 3.0 2022-08-23
*/
@RestController
@RequestMapping("AppUserCode/appusercode")
@Api(tags="用户验证码")
public class AppUserCodeController {
    @Autowired
    private AppUserCodeService appUserCodeService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("AppUserCode:appusercode:page")
    public Result<PageData<AppUserCodeDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<AppUserCodeDTO> page = appUserCodeService.page(params);

        return new Result<PageData<AppUserCodeDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("AppUserCode:appusercode:info")
    public Result<AppUserCodeDTO> get(@PathVariable("id") Long id){
        AppUserCodeDTO data = appUserCodeService.get(id);

        return new Result<AppUserCodeDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("AppUserCode:appusercode:save")
    public Result save(@RequestBody AppUserCodeDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        appUserCodeService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("AppUserCode:appusercode:update")
    public Result update(@RequestBody AppUserCodeDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        appUserCodeService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("AppUserCode:appusercode:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        appUserCodeService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("AppUserCode:appusercode:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<AppUserCodeDTO> list = appUserCodeService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "用户验证码", list, AppUserCodeExcel.class);
    }

}