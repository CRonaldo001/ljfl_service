package io.renren.modules.AppMemberLevel.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import java.util.Date;

/**
 * 积分等级
 *
 * @author cxy 
 * @since 3.0 2022-08-26
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class AppMemberLevelExcel {
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
    @ExcelProperty(value = "等级", index = 6)
    private String level;
    @ExcelProperty(value = "等级名称", index = 7)
    private String levelName;
    @ExcelProperty(value = "积分下限", index = 8)
    private Integer integralLower;
    @ExcelProperty(value = "积分上限", index = 9)
    private Integer integralUpper;
    @ExcelProperty(value = "积分转让费率", index = 11)
    private Double serviceCharge;
    @ExcelProperty(value = "备注", index = 12)
    private String remark;
}