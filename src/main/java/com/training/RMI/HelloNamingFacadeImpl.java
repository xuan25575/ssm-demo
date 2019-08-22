package com.training.RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

/**
 * @Description  UnicastRemoteObject 继承才能序列化该类.
 * 知识点： 父子类序列化--》 父类实现了序列化子类也可以参与序列化.
 * @date 2019/8/16
 */
public class HelloNamingFacadeImpl extends UnicastRemoteObject implements HelloNamingFacade  {

    protected HelloNamingFacadeImpl() throws RemoteException {
        super();
    }

    @Override
    public String helloWorld(String name) throws RemoteException {
        return "[Registry] 你好! " + name;
    }

    public static void main(String[] args) {
        String s = UUID.randomUUID().toString().replace("-","").substring(20);
        System.out.println(s);
    }
}
