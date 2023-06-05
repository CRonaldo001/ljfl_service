package io.renren.modules.AppSchoolCollege.controller;

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
import io.renren.modules.AppSchoolCollege.dto.AppSchoolCollegeDTO;
import io.renren.modules.AppSchoolCollege.excel.AppSchoolCollegeExcel;
import io.renren.modules.AppSchoolCollege.service.AppSchoolCollegeService;
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
* 学院
*
* @author WEI 
* @since 3.0 2023-03-20
*/
@RestController
@RequestMapping("AppSchoolCollege/appschoolcollege")
@Api(tags="学院")
public class AppSchoolCollegeController {
    @Autowired
    private AppSchoolCollegeService appSchoolCollegeService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("AppSchoolCollege:appschoolcollege:page")
    public Result<PageData<AppSchoolCollegeDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<AppSchoolCollegeDTO> page = appSchoolCollegeService.page(params);

        return new Result<PageData<AppSchoolCollegeDTO>>().ok(page);
    }




    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("AppSchoolCollege:appschoolcollege:info")
    public Result<AppSchoolCollegeDTO> get(@PathVariable("id") Long id){
        AppSchoolCollegeDTO data = appSchoolCollegeService.get(id);

        return new Result<AppSchoolCollegeDTO>().ok(data);
    }





    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("AppSchoolCollege:appschoolcollege:save")
    public Result save(@RequestBody AppSchoolCollegeDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        appSchoolCollegeService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("AppSchoolCollege:appschoolcollege:update")
    public Result update(@RequestBody AppSchoolCollegeDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        appSchoolCollegeService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("AppSchoolCollege:appschoolcollege:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        appSchoolCollegeService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("AppSchoolCollege:appschoolcollege:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<AppSchoolCollegeDTO> list = appSchoolCollegeService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "学院", list, AppSchoolCollegeExcel.class);
    }

}