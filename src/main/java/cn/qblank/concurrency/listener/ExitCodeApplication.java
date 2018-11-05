package cn.qblank.concurrency.listener;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @date 2018/11/5
 * JVM退出时执行
 */
@SpringBootApplication
public class ExitCodeApplication {

    @Bean
    public ExitCodeGenerator exitCodeGenerator(){
        return new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                return 110;
            }
        };
    }

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(ExitCodeApplication.class,args)));
    }
}
