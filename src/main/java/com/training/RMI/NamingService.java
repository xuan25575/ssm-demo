package com.training.RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @Description NamingService
 * @date 2019/8/16
 */
public class NamingService {
    public static void main(String[] args) {
        try {
            // 本地主机上的远程对象注册表Registry的实例
            LocateRegistry.createRegistry(1100);
            // 创建一个远程对象
            HelloNamingFacade hello = new HelloNamingFacadeImpl();
            // 把远程对象注册到RMI注册服务器上，并命名为Hello
            //绑定的URL标准格式为：rmi://host:port/name
            Naming.bind("rmi://localhost:1100/HelloNaming", hello);
            System.out.println("======= 启动RMI服务成功! =======");
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }
}
