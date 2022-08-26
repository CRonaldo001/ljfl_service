package io.renren.modules.AppMemberLevel.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.modules.AppMemberLevel.dto.AppMemberLevelDTO;
import io.renren.modules.AppMemberLevel.excel.AppMemberLevelExcel;
import io.renren.modules.AppMemberLevel.service.AppMemberLevelService;
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
* 积分等级
*
* @author cxy 
* @since 3.0 2022-08-26
*/
@RestController
@RequestMapping("AppMemberLevel/appmemberlevel")
@Api(tags="积分等级")
public class AppMemberLevelController {
    @Autowired
    private AppMemberLevelService appMemberLevelService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("AppMemberLevel:appmemberlevel:page")
    public Result<PageData<AppMemberLevelDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put("orderField","level");
        params.put("order","asc"); // 此处修改后需要改 updateLevelInfo 方法
        PageData<AppMemberLevelDTO> page = appMemberLevelService.page(params);

        return new Result<PageData<AppMemberLevelDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("AppMemberLevel:appmemberlevel:info")
    public Result<AppMemberLevelDTO> get(@PathVariable("id") Long id){
        AppMemberLevelDTO data = appMemberLevelService.get(id);

        return new Result<AppMemberLevelDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("AppMemberLevel:appmemberlevel:save")
    public Result save(@RequestBody AppMemberLevelDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        appMemberLevelService.save(dto);
        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("AppMemberLevel:appmemberlevel:update")
    public Result update(@RequestBody AppMemberLevelDTO dto){
        if(null==dto.getList()){
            return new Result().error("保存配置失败，请联系管理员");
        }
        return appMemberLevelService.updateLevelInfo(dto);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("AppMemberLevel:appmemberlevel:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        appMemberLevelService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("AppMemberLevel:appmemberlevel:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<AppMemberLevelDTO> list = appMemberLevelService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "积分等级", list, AppMemberLevelExcel.class);
    }

}