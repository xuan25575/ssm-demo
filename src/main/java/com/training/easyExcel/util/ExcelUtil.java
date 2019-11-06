package com.training.easyExcel.util;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.FillPatternType;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;


/**
 * @Description  简单excel导出工具类
 * @Author xuanyu.huang
 *
 *  <p> excel对应的实体对象
 *  @see ExcelProperty 放置在实体对象的属性上 类似@ExcelProperty("字符串标题")
 *  <p> 使用{@link ExcelProperty}注解指定写入的列，它可以接受converter，
 *  实现{@link com.alibaba.excel.converters.Converter} Converter 来自定义Excel中数据和写出数据（方法传入list中的数据）的转换。
 *  一般来说可以先处理好数据传入即可。
 *  使用{@link ExcelProperty}注解指定复杂的头
 *  具体参考 https://alibaba-easyexcel.github.io/quickstart/write.html#%E5%A4%8D%E6%9D%82%E5%A4%B4%E5%86%99%E5%85%A5
 *  </p>
 *  @see  ExcelIgnore <p> 忽略这个字段</p>
 *  @see ContentRowHeight
 *  @see HeadRowHeight
 *  @see ColumnWidth
 *  <p>使用注解{@link ColumnWidth}、{@link HeadRowHeight}、{@link ContentRowHeight}指定宽度或高度</p>
 *  @see DateTimeFormat
 *  <p>指定 年月日的格式 如：@DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")</p>
 *  @see  NumberFormat
 *  <p>指定数字格式 用百分比表示@NumberFormat("#.##%")</p>
 *
 *  使用的版本
 *   <version>2.1.0-beta4</version>
 */
public class ExcelUtil<T> {


    /**
     *  通用写出   通过该 ExcelProperty 等，传入数据即可。
     * @param list  传入的数据（excel对应的实体对象 list）
     * @param fileName  文件名
     * @param clazz  excel对应的实体对象的class
     * @param response
     * @throws Exception
     */
    public void writeToExcel(List<T> list, String fileName, Class<T> clazz,HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName2 = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName2 + ".xlsx");
        EasyExcel.write(response.getOutputStream(), clazz).sheet().doWrite(list);
    }

    /**
     *  较为通用
     *  导出自动匹配列的宽度 （不是很精确）足够用了
     * @param list 传入的数据（excel对应的实体对象 list）
     * @param fileName 文件名
     * @param clazz excel对应的实体对象的class
     * @param response  response
     * @throws Exception
     */
    public void writeToExcelWithMatchColumnWidth(List<T> list, String fileName, Class<T> clazz,HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName2 = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName2 + ".xlsx");
        EasyExcel.write(response.getOutputStream(), clazz)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet().doWrite(list);
    }


    /**
     *  根据自定义模板写出 （需要系统中有一个放置模板的空间）
     * @param list 传入的数据（excel对应的实体对象 list）
     * @param templateFileName 模板名字
     * @param clazz  excel对应的实体对象的class
     * @param response response
     * @throws Exception
     */
    public void writeToExcelWithTemplate(List<T> list,String templateFileName,Class<T> clazz,String fileName,
                                         HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName2 = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName2 + ".xlsx");
        String templateFileName2 = templateFileName + ".xlsx";
        // 这里需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(response.getOutputStream(),clazz).withTemplate(templateFileName2).sheet().doWrite(list);
    }

    /**
     *  导出需要排除的列
     * @param list  传入的数据（excel对应的实体对象 list）
     * @param excludeColumnFiledNames 根据用户传入字段 假设我们要忽略 date
     * @param clazz  excel对应的实体对象的class
     * @param response response
     * @throws Exception
     */
    public void writeToExcelWithExclude(List<T> list, Collection<String> excludeColumnFiledNames,String fileName,
                                        Class<T> clazz, HttpServletResponse response) throws Exception{

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName2 = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName2 + ".xlsx");
        EasyExcel.write(response.getOutputStream(), clazz)
                .excludeColumnFiledNames(excludeColumnFiledNames).sheet().doWrite(list);

    }

    /**
     * 导出 includeColumnFiledNames 包括的列
     * @param list 传入的数据（excel对应的实体对象 list）
     * @param includeColumnFiledNames  根据用户传入字段导出需要的列
     * @param clazz excel对应的实体对象的class
     * @param response
     * @throws Exception
     */
    public void writeToExcelWithInclude(List<T> list, Collection<String> includeColumnFiledNames,String fileName,
                                        Class<T> clazz, HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName2 = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName2 + ".xlsx");
        EasyExcel.write(response.getOutputStream(), clazz)
                .includeColumnFiledNames(includeColumnFiledNames).sheet()
                .doWrite(list);
    }


    /**
     *
     * @param list 传入的数据（excel对应的实体对象 list）
     * @param fileName 文件名
     * @param clazz excel对应的实体对象的class
     * @param response response
     * @param eachRow  每隔几行会合并（确定那一列合并）
     * @param columnIndex 第几列索引,最小是0
     *  <p> eachRow =2  columnIndex =0    那么0列  每2行合并为一行。
     * @throws Exception
     */
    public void writeToExcelWithMergeCell(List<T> list, String fileName,
                                         Class<T> clazz, HttpServletResponse response,int eachRow, int columnIndex) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName2 = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName2 + ".xlsx");
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(eachRow, columnIndex);
        EasyExcel.write(response.getOutputStream(), clazz)
                .registerWriteHandler(loopMergeStrategy)
                .sheet().doWrite(list);
    }

    /**
     *  自定义样式写出
     * @param list  传入的数据（excel对应的实体对象 list）
     * @param fileName 文件名
     * @param headWriteCellStyle 头的样式 -- 头的策略
     * @param contentWriteCellStyle 内容的样式-- 内容的策略
     * @param clazz  excel对应的实体对象的class
     * @param response  response
     * @throws Exception
     */
    public void writeToExcelWithStyle(List<T> list,String fileName,WriteCellStyle headWriteCellStyle,WriteCellStyle contentWriteCellStyle,
                                        Class<T> clazz, HttpServletResponse response) throws Exception{

//        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
//        WriteFont headWriteFont = new WriteFont();
//        headWriteFont.setFontHeightInPoints((short)20);
//        headWriteCellStyle.setWriteFont(headWriteFont);

        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND
        // 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
//        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
//        WriteFont contentWriteFont = new WriteFont();
//        // 字体大小
//        contentWriteFont.setFontHeightInPoints((short)20);
//        contentWriteCellStyle.setWriteFont(contentWriteFont);

        // 这个策略是 头是头的样式 内容是内容的样式 策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName2 = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName2 + ".xlsx");
        EasyExcel.write(response.getOutputStream(), clazz)
                .registerWriteHandler(horizontalCellStyleStrategy).sheet()
                .doWrite(list);
    }


    /**
     *  动态头，实时生成头写入
     * @param list  传入的数据（excel对应的实体对象 list）
     * @param fileName 文件名
     * @param head Excel 标题（头） List<List<String>>  List<String> 里面是sheet页一个头标题  List<List<String>>  sheet页的多个标题
     * @param response  response
     * @throws Exception
     */
    public void writeToExcelWithDynamicHead(List<T> list,String fileName,List<List<String>> head, HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName2 = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName2 + ".xlsx");
        EasyExcel.write(response.getOutputStream()).head(head).sheet().doWrite(list);
    }



}
