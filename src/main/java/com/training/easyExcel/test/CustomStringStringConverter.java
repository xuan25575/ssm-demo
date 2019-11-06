package com.training.easyExcel.test;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class CustomStringStringConverter implements Converter<String> {



    // 比如我们有个需求   导入的Excel 填写男 ，女汉字 但是数据库需要存0 -- 男 1----女
    // supportJavaTypeKey 方法 支持 Integer 类  convertToJavaData 方法中转换即可。
    public static final String MALE = "男";
    public static final String FEMALE = "女";


//    @Override
//    public Class supportJavaTypeKey() {
//        return Integer.class;
//    }

//    public Integer convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
//        String stringValue = cellData.getStringValue();
//        if (MALE.equals(stringValue)){
//            return 1;
//        }else {
//            return 2;
//        }
//    }



    //支持java类型
    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    // excel 的类型
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 这里读的时候会调用
     *
     * @param cellData
     *            NotNull
     * @param contentProperty
     *            Nullable
     * @param globalConfiguration
     *            NotNull
     * @return
     */
    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                    GlobalConfiguration globalConfiguration) {



        return "自定义：" + cellData.getStringValue();
    }
















    /**
     * 这里是写的时候会调用 不用管
     *
     * @param value
     *            NotNull
     * @param contentProperty
     *            Nullable
     * @param globalConfiguration
     *            NotNull
     * @return
     */
    @Override
    public CellData convertToExcelData(String value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) {
        return new CellData(value);
    }
}
