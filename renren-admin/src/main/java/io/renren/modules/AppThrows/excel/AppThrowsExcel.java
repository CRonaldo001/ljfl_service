package io.renren.modules.AppThrows.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import java.util.Date;

/**
 * 投放记录
 *
 * @author WEI 
 * @since 3.0 2022-08-29
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class AppThrowsExcel {
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
    private String mainboardId;
    @ExcelProperty(value = "桶号", index = 8)
    private Integer no;
    @ExcelProperty(value = "刷卡号", index = 9)
    private String swipeNo;
    @ExcelProperty(value = "垃圾类型编号", index = 10)
    private Integer garbageTypeCode;
    @ExcelProperty(value = "垃圾类型", index = 11)
    private String garbageTypeName;
    @ExcelProperty(value = "手机号", index = 12)
    private String mobilePhone;
    @ExcelProperty(value = "姓名", index = 13)
    private String realName;
    @ExcelProperty(value = "重量", index = 14)
    private Double weight;
    @ExcelProperty(value = "照片", index = 15)
    private String deviceImg;
}