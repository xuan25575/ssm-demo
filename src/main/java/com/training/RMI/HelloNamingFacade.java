package com.training.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Description TODO
 * @date 2019/8/16
 */
public interface HelloRegistryFacade extends Remote {
    String helloWorld(String name) throws RemoteException;
}
