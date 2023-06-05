package io.renren.modules.AppSchoolBuilding.controller;

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
import io.renren.modules.AppSchoolBuilding.dto.AppSchoolBuildingDTO;
import io.renren.modules.AppSchoolBuilding.excel.AppSchoolBuildingExcel;
import io.renren.modules.AppSchoolBuilding.service.AppSchoolBuildingService;
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
* 楼栋
*
* @author WEI 
* @since 3.0 2023-03-20
*/
@RestController
@RequestMapping("AppSchoolBuilding/appschoolbuilding")
@Api(tags="楼栋")
public class AppSchoolBuildingController {
    @Autowired
    private AppSchoolBuildingService appSchoolBuildingService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("AppSchoolBuilding:appschoolbuilding:page")
    public Result<PageData<AppSchoolBuildingDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<AppSchoolBuildingDTO> page = appSchoolBuildingService.page(params);

        return new Result<PageData<AppSchoolBuildingDTO>>().ok(page);
    }


    @GetMapping("getListBySchoolId")
    public Result<List<AppSchoolBuildingDTO>> getListBySchoolId(@ApiIgnore @RequestParam Map<String, Object> params){
        List<AppSchoolBuildingDTO> page = appSchoolBuildingService.getListBySchoolId(params);

        return new Result<List<AppSchoolBuildingDTO>>().ok(page);
    }



    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("AppSchoolBuilding:appschoolbuilding:info")
    public Result<AppSchoolBuildingDTO> get(@PathVariable("id") Long id){
        AppSchoolBuildingDTO data = appSchoolBuildingService.get(id);

        return new Result<AppSchoolBuildingDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("AppSchoolBuilding:appschoolbuilding:save")
    public Result save(@RequestBody AppSchoolBuildingDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        appSchoolBuildingService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("AppSchoolBuilding:appschoolbuilding:update")
    public Result update(@RequestBody AppSchoolBuildingDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        appSchoolBuildingService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("AppSchoolBuilding:appschoolbuilding:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        appSchoolBuildingService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("AppSchoolBuilding:appschoolbuilding:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<AppSchoolBuildingDTO> list = appSchoolBuildingService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "楼栋", list, AppSchoolBuildingExcel.class);
    }

}