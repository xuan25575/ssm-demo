package com.training.easyExcel.web;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @date 2019/10/28 18:33
 */
@Data
public class DemoData {

    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
     private Date  date;
    @ExcelProperty("数字标题")
    private double doubleData;

}
