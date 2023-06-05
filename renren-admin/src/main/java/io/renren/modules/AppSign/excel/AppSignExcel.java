package io.renren.modules.AppSign.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import java.util.Date;

/**
 * 签到
 *
 * @author WEI 
 * @since 3.0 2022-08-26
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class AppSignExcel {
    @ExcelProperty(value = "id", index = 0)
    private Long id;
    @ExcelProperty(value = "备注", index = 1)
    private String remark;
    @ExcelProperty(value = "学校编号", index = 2)
    private Long schoolCode;
    @ExcelProperty(value = "租户编码", index = 3)
    private Long tenantCode;
    @ExcelProperty(value = "创建者", index = 4)
    private Long creator;
    @ExcelProperty(value = "创建时间", index = 5)
    private Date createDate;
    @ExcelProperty(value = "更新者", index = 6)
    private Long updater;
    @ExcelProperty(value = "更新时间", index = 7)
    private Date updateDate;
    @ExcelProperty(value = "Long", index = 8)
    private Long userId;
}