package com.training.RMI;

import java.rmi.RemoteException;

/**
 * @Description TODO
 * @date 2019/8/16
 */
public class HelloRegistryFacadeImpl implements HelloRegistryFacade {
    @Override
    public String helloWorld(String name) throws RemoteException {
        return "[Registry] 你好! " + name;
    }
}
