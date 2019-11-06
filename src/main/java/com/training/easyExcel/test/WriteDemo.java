package com.training.easyExcel.test;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WriteDemo {

    private List<DemoData2> data() {
        List<DemoData2> list = new ArrayList<DemoData2>();
        for (int i = 0; i < 10; i++) {
            DemoData2 data = new DemoData2();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    public static void main(String[] args) {
        String path1 = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String path = WriteDemo.class.getClassLoader().getResource("").getPath();
        String property =System.getProperty("user.dir");
        URL resource = WriteDemo.class.getResource("");

        System.out.println(path);
        System.out.println(path1);
        System.out.println(property);
        System.out.println(resource.getPath());

    }

    @Test
    // 简单写
    public void simpleWrite() {
        String path = new File("D:/work").getPath();
        // 写法1
        String fileName =  path+ "/simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData2.class).sheet("模板").doWrite(data());

        // 写法2
//        fileName =  "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData2.class).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//        excelWriter.write(data(), writeSheet);
//        /// 千万别忘记finish 会帮忙关闭流
//        excelWriter.finish();

    }

    @Test
    // 复杂表头写出
    public void complexWrite() {
        String path = new File("D:\\work").getPath();
        // 写法1
        String fileName =  path+ "/complexWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, MultiLineHeadExcelModel.class).sheet("模板").doWrite(date2());
    }

    private List<MultiLineHeadExcelModel> date2(){
        List<MultiLineHeadExcelModel> list = new ArrayList<MultiLineHeadExcelModel>();
        for (int i = 0; i < 10; i++) {
            MultiLineHeadExcelModel data = new MultiLineHeadExcelModel();
            data.setP1("字符串P1"+i);
            data.setP2("字符串P2"+i);
            data.setP3("字符串P3"+i);
            data.setP4("字符串P4"+i);
            data.setP5("字符串P5"+i);
            data.setP6("字符串P6"+i);
            data.setP7("字符串P7"+i);
            data.setP8("字符串P8"+i);
            data.setP9("字符串P9"+i);
            list.add(data);
        }
        return list;
    }


    // 自定义样式
    @Test
    public void styleWrite() {
        String fileName = new File("D:\\work").getPath()+"/styleWrite" + System.currentTimeMillis() + ".xlsx";
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)20);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)20);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData2.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("模板")
                .doWrite(data());
    }


    @Test
    // 自动列宽
    public void longestMatchColumnWidthWrite() {
        String fileName = new File("D:\\work").getPath()+"/longestMatchColumnWidthWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData2.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("模板").doWrite(dataLong());
    }

    private List<DemoData2> dataLong() {
        List<DemoData2> list = new ArrayList<DemoData2>();
        for (int i = 0; i < 10; i++) {
            DemoData2 data = new DemoData2();
            data.setString("测试很长的字符串测试很长的字符串测试很长的字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(1000000000000.0);
            list.add(data);
        }
        return list;
    }



}
