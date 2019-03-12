package cn.qblank.concurrency.controller;

import cn.qblank.concurrency.cache.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 1.0
 * @date 2019/3/12 16:45
 */
@Controller
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private RedisClient redisClient;

    @RequestMapping("/set")
    @ResponseBody
    public String set(@RequestParam("key") String key,
                      @RequestParam("value") String value) throws Exception{
        redisClient.set(key,value);
        return "success";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestParam("key") String key) throws Exception{
        return redisClient.get(key);
    }
}
