package com.training.CFX;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;


// 需要配置jetty 容器依赖
public class JaxWsServer {
    public static void main(String[] args) {
        JaxWsServerFactoryBean factory  = new JaxWsServerFactoryBean();
        factory.setAddress("http://localhost:8080/ws/soap/hello");
        factory.setServiceClass(HelloService.class);
        factory.setServiceBean(new HelloServiceImpl());
        factory.create();
        System.out.println("soap ws is published");
    }
}
