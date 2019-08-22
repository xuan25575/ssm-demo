package com.training.RMI;

import java.rmi.Naming;

/**
 * @Description NamingClient
 * @date 2019/8/16
 */
public class NamingClient {

    public static void main(String[] args) {
        try {
            String remoteAddr="rmi://localhost:1100/HelloNaming";
            HelloNamingFacade hello = (HelloNamingFacade) Naming.lookup(remoteAddr);
            String response = hello.helloWorld("ZhenJin");
            System.out.println("=======> " + response + " <=======");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
