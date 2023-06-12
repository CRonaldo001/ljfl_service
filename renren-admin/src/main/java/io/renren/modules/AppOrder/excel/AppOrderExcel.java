package io.renren.modules.AppOrder.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 兑换记录
 *
 * @author WEI
 * @since 3.0 2022-08-13
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class AppOrderExcel {
    @ExcelProperty(value = "兑换人", index = 0)
    private String userName;
    @ExcelProperty(value = "电话号码", index = 1)
    private String userPhone;
    @ExcelProperty(value = "商品名称", index = 2)
    private String goodsName;
    @ExcelProperty(value = "商品數量", index = 3)
    private Integer goodsCount;
    @ExcelProperty(value = "兑换码", index = 4)
    private String transactionId;
    @ExcelProperty(value = "订单状态", index = 5)
    private String status;
    @ExcelProperty(value = "下单时间", index = 6)
    private Date createDate;
    //    @ExcelProperty(value = "兑换时间", index = 7)
//    private Date updateDate;
    @ExcelProperty(value = "送货方式", index = 7)
    private String sendType;
    @ExcelProperty(value = "收货地址", index = 8)
    private String addr;


}