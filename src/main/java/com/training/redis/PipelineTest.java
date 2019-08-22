package com.training.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.util.Pool;

import java.util.List;

/**
 * @Description Pipeline 测试
 * @date 2019/8/20
 */
public class PipelineTest {
    public static void main(String[] args) {
        JedisPool  pool = new JedisPool("127.0.0.1",6379);
        Jedis jedis =pool.getResource();
        long start = System.currentTimeMillis();
        Pipeline pipelined = jedis.pipelined();
        
        // 测试 10 万读写
        for (int i = 0; i < 100000; i++) {
            int j = i+1;
            pipelined.set("pipelined_key_"+j,"pipelined_value_"+j);
            pipelined.get("pipelined_key_"+j);
        }
        List<Object> list = pipelined.syncAndReturnAll();
        long end = System.currentTimeMillis();
        // 耗时463毫秒
        System.out.println("耗时"+(end - start)+ "毫秒");
    }
}
