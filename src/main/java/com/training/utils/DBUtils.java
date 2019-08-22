package com.training.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @Description TODO
 * @date 2019/8/14
 */
public class DBUtils {
    private static String driver;
    static {

        try {
            InputStream in = new FileInputStream(new File("D:\\ideaspace\\ssmdemo\\src\\main\\resources\\jdbc.properties"));
            Properties properties = new Properties();
            properties.load(in);
            driver =  properties.getProperty("jdbc.driverClasss");
            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssm","root","root");


        System.out.println();

    }
}
