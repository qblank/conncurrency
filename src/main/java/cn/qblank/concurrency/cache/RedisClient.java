package cn.qblank.concurrency.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @date 2019/3/12 16:39
 */
@Slf4j
@Component
public class RedisClient {
    @Resource(name = "jedisPool")
    private JedisPool jedisPool;

    public void set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key,value);
        }catch (Exception e){
            log.error("e:{}",e);
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }

    public String get(String key) throws Exception{
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }
}
