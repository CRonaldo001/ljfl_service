package io.renren.modules.AppSchoolCollege.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import java.util.Date;

/**
 * 学院
 *
 * @author WEI 
 * @since 3.0 2023-03-20
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class AppSchoolCollegeExcel {
    @ExcelProperty(value = "id", index = 0)
    private Long id;
    @ExcelProperty(value = "租户编码", index = 1)
    private Long tenantCode;
    @ExcelProperty(value = "创建者", index = 2)
    private Long creator;
    @ExcelProperty(value = "创建时间", index = 3)
    private Date createDate;
    @ExcelProperty(value = "更新者", index = 4)
    private Long updater;
    @ExcelProperty(value = "更新时间", index = 5)
    private Date updateDate;
    @ExcelProperty(value = "Long", index = 6)
    private Long schoolId;
    @ExcelProperty(value = "学院名称", index = 7)
    private String collegeName;
    @ExcelProperty(value = "备注", index = 8)
    private String remark;
}