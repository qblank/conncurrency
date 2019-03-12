package cn.qblank.concurrency.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @version 1.0
 * @date 2019/3/12 16:27
 */
@Configuration
public class RedisConfig {

    @Bean(name = "jedisPool")
    public JedisPool jedisPool(@Value("${redis.host}") String host,
                               @Value("${redis.port}") String port){
        return new JedisPool(host,Integer.parseInt(port));
    }


}
