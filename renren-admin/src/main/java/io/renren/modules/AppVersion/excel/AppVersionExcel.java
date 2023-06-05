package io.renren.modules.AppVersion.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import java.util.Date;

/**
 * app版本
 *
 * @author WEI 
 * @since 3.0 2022-09-20
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class AppVersionExcel {
    @ExcelProperty(value = "id", index = 0)
    private Long id;
    @ExcelProperty(value = "备注", index = 1)
    private String remark;
    @ExcelProperty(value = "租户编码", index = 2)
    private Long tenantCode;
    @ExcelProperty(value = "创建者", index = 3)
    private Long creator;
    @ExcelProperty(value = "创建时间", index = 4)
    private Date createDate;
    @ExcelProperty(value = "更新者", index = 5)
    private Long updater;
    @ExcelProperty(value = "更新时间", index = 6)
    private Date updateDate;
    @ExcelProperty(value = "String", index = 7)
    private String appid;
    @ExcelProperty(value = "String", index = 8)
    private String contents;
    @ExcelProperty(value = "Integer", index = 9)
    private Integer isMandatory;
    @ExcelProperty(value = "Integer", index = 10)
    private Integer isSilently;
    @ExcelProperty(value = "String", index = 11)
    private String name;
    @ExcelProperty(value = "String", index = 12)
    private String paltform;
    @ExcelProperty(value = "Integer", index = 13)
    private Integer stablePublish;
    @ExcelProperty(value = "String", index = 14)
    private String title;
    @ExcelProperty(value = "String", index = 15)
    private String type;
    @ExcelProperty(value = "String", index = 16)
    private String url;
    @ExcelProperty(value = "String", index = 17)
    private String version;
}