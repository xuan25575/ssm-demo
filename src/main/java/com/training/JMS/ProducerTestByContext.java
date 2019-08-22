package com.training.JMS;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.Destination;

/**
 * @Description ProducerTestByContext 测试
 * @date 2019/8/16
 */
public class ProducerTestByContext {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-jms.xml");
        ProducerService service = context.getBean(ProducerService.class);
        Destination destination = (Destination)context.getBean("queueDestination");

        //进行发送消息
        for (int i = 0; i < 10 ; i++) {
            service.sendMessage(destination, "你好，消费者！这是消息：" + (i+1));
        }
        //当消息发送完后，关闭容器
        context.close();
    }

}
