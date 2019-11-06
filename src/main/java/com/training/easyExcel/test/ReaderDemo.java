package com.training.easyExcel.test;

import com.alibaba.excel.EasyExcel;
import com.training.easyExcel.web.DemoData;
import com.training.easyExcel.web.UploadData;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

public class ReaderDemo {



    // 文件上传后，   通过listen 读取数据（观察者模式-- 事件监听者模式）


    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
//        String fileName = new File("D:\\work").getPath()+"/read" + System.currentTimeMillis() + ".xlsx";
        String fileName = "D:\\work/worksimpleWrite1573031839566.xlsx";



        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();

        // 写法2：
//        ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
//        ReadSheet readSheet = EasyExcel.readSheet(0).build();
//        excelReader.read(readSheet);
//        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//        excelReader.finish();
    }



    @Test
    public void simpleRead2() {
        String fileName = "D:\\work/worksimpleWrite1573031839566.xlsx";
        EasyExcel.read(fileName, ConverterData.class, new ConverterDataListener()).sheet().doRead();

    }

    @Test
    public void simpleRead3() {
        try {
            String fileName = "D:\\work/worksimpleWrite1573031839566.xlsx";
            EasyExcel.read(fileName, DemoData.class, new ConverterDataListener()).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //    @PostMapping("upload")
//    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), UploadData.class, new DemoDataListener(new DemoDAO())).sheet().doRead();
        return "success";
    }

}
