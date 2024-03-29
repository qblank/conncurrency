package cn.qblank.concurrency.publish;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @date 2018/10/30
 * 不安全的发布对象
 */
@Slf4j
public class UnsafePublish {
    private String[] states = {"a","b","c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}",Arrays.toString(unsafePublish.getStates()));
        unsafePublish.getStates()[0] = "d";
        log.info("{}",Arrays.toString(unsafePublish.getStates()));

    }

}
