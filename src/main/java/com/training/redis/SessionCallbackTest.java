package com.training.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.List;

/**
 * @Description redis 事务测试
 * @date 2019/8/20
 */
public class SessionCallbackTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");
        RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
//        SessionCallback callback = (SessionCallback)(RedisOperations ops) -> {
//            ops.multi();
//            ops.boundValueOps("key1").set("value1");
//            String value  = (String)ops.boundValueOps("key1").get();
//            System.out.println("事务执行中，命令进入队列，没有被执行吗，所以value 为空"+ value);
//            List list = ops.exec();
//            value = (String)redisTemplate.opsForValue().get("key1");
//            return value;
//        };
        SessionCallback callback = new SessionCallback(){

            @Override
            public Object execute(RedisOperations ops) throws DataAccessException {
                ops.multi();
                ops.boundValueOps("key1").set("value1");
                String value = (String) ops.boundValueOps("key1").get();
                System.out.println("事务执行中，命令进入队列，没有被执行吗，所以value 为空" + value);
                List list = ops.exec();
                value = (String) redisTemplate.opsForValue().get("key1");
                return value;
            }
        };
        String value  = (String) redisTemplate.execute(callback);
        System.out.println(value);
    }
}
