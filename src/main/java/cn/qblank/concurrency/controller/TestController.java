package cn.qblank.concurrency.controller;

import cn.qblank.concurrency.ExternalizedConfiguration.TypeSafeConfigureProerties.FooProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date 2018/10/29
 */
@Controller
@Slf4j
public class TestController {

    @Value("${my.number.in.range}")
    private String value;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        log.info("测试");
        return "测试";
    }

    @GetMapping("/random")
    @ResponseBody
    public String randomTest(){
        log.info("random value:{}",value);
        return value;
    }
}
