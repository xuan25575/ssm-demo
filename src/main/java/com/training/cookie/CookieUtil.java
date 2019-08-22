package com.training.cookie;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * @Description CookieUtil  cookie压缩和解压
 * @date 2019/8/20
 */
public class CookieUtil {

    /**
     *  压缩 编码
     * @param c Cookie
     * @param response HttpServletResponse
     */
    public void compressCookie(Cookie c, HttpServletResponse response){
        try {
            ByteArrayOutputStream bos =  new ByteArrayOutputStream();
            DeflaterOutputStream dos = new DeflaterOutputStream(bos);
            dos.write(c.getValue().getBytes());
            dos.close();
            System.out.println("before compress length :"+c.getValue().getBytes().length);
            String compress = new BASE64Encoder().encode(bos.toByteArray());
            response.addCookie(new Cookie("compress",compress));
            System.out.println("after compress length "+ compress.getBytes().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  解压解码
     * @param cookie Cookie
     */
    public void unCompressCookie(Cookie cookie){
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] compress = new BASE64Decoder().
                    decodeBuffer(new String(cookie.getValue().getBytes()));
            ByteArrayInputStream bis = new ByteArrayInputStream(compress);
            InflaterInputStream iis = new InflaterInputStream(bis);
            byte[] b = new byte[1024];
            int count;
            while((count= iis.read(b))!= -1){
                out.write(b,0,count);
            }
            iis.close();
            System.out.println(out.toByteArray());
            System.out.println(new String(out.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 服务器向客户端颁发cookie，在颁发给客户端时先对cookie进行压缩
     * @param response
     * @param name cookie名字
     * @param value cookie值
     * @throws IOException
     * */
    public static void addCookie(HttpServletResponse response, String name, String value) throws IOException {
        System.out.println("进入了方法体...");

        //进行cookie进行压缩
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DeflaterOutputStream dos = new DeflaterOutputStream(bos);

        dos.write(name.getBytes());
        dos.close();
        System.out.println("name: " + name);
        System.out.println("name before compress length: " + name.length());
        String compressName = URLEncoder.encode(new String(bos.toByteArray()), "UTF-8");
//        String compressName = new BASE64Encoder().encode(bos.toByteArray());
        System.out.println("name after compress length: " + compressName.length());
        System.out.println("compressName: " + compressName);

        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        DeflaterOutputStream dos1 = new DeflaterOutputStream(bos1);

        dos1.write(value.getBytes());
        dos1.close();
        System.out.println("value: " + value);
        System.out.println("value before compress length: " + value.length());
        //压缩以后进行编码
        String compressValue = URLEncoder.encode(new String(bos.toByteArray()), "UTF-8");
        System.out.println("value after compress length: " + compressValue.length());
        System.out.println("compressValue: " + compressValue);

        //压缩以后需要使用使用base64进行编码,因为压缩以后存在控制字符，不符合cookie标准
        //        String lastName = URLEncoder.encode(compressName, "UTF-8");
        //        String lastValue = URLEncoder.encode(compressValue, "UTF-8");
        //        System.out.println("lastName: " + lastName);
        //        System.out.println("lastValue: " + lastValue);

        Cookie cookie = new Cookie(compressName, compressValue);
        cookie.setPath("/");  //本域名下contextPath都可以访问该Cookie

        cookie.setMaxAge(Integer.MAX_VALUE);  //失永远保存在浏览器，除非人为进行清理
        System.out.println("发送结束...");
        //unCompressCookie(cookie);
        response.addCookie(cookie);  //向客户端发送cookie
    }
}
