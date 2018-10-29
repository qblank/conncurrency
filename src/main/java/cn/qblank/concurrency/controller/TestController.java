package cn.qblank.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date 2018/10/29
 */
@Controller
@Slf4j
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        log.info("测试");
        return "测试";
    }
}
