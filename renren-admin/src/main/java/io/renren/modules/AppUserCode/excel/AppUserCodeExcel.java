package io.renren.modules.AppUserCode.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import java.util.Date;

/**
 * 用户验证码
 *
 * @author WEI 
 * @since 3.0 2022-08-23
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class AppUserCodeExcel {
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
    @ExcelProperty(value = "用户id", index = 7)
    private Long userId;
    @ExcelProperty(value = "验证码", index = 8)
    private String code;
    @ExcelProperty(value = "Date", index = 9)
    private Date expiredDate;
}