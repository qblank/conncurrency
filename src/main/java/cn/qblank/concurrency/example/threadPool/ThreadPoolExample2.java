package cn.qblank.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version 1.0
 * @date 2019/3/10 18:03
 */
@Slf4j
public class ThreadPoolExample2 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executor.execute(() -> {
               log.info("task{}", index);
            });
        }
        executor.shutdown();
    }
}
