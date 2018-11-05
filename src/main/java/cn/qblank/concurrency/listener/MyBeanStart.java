package cn.qblank.concurrency.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @date 2018/11/5
 * SpringBoot启动时执行
 */
@Slf4j
@Component
public class MyBeanStart implements ApplicationRunner {
    /*@Override
    public void run(String... strings) throws Exception {
        log.info("监听器启动...");
    }*/

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("监听器启动");
    }
}
