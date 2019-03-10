package cn.qblank.concurrency.ExternalizedConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @date 2018/11/5
 */
@Component
public class MyBean {
    @Value("${my.secret}")
    private static String randomValue;

    public static void main(String[] args) {
        System.out.println(randomValue);
    }
}
