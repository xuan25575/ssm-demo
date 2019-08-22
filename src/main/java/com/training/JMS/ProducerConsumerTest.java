package com.training.JMS;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

/**
 * @Description ProducerConsumerTest  测试类
 * @date 2019/8/16
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-jms.xml")
public class ProducerConsumerTest {
    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;

    @Test
    public void testSend() {
        for (int i=0; i<2; i++) {
            producerService.sendMessage(destination, "你好，消费者！这是消息：" + (i+1));
        }
    }

}
