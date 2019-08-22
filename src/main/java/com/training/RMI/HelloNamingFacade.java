package com.training.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

/**
 * @Description TODO
 * @date 2019/8/16
 */
public interface HelloNamingFacade extends Remote {
    String helloWorld(String name) throws RemoteException;




}
