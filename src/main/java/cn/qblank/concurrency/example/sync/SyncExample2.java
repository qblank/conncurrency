package cn.qblank.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @date 2018/10/29
 * synchronized关键字
 *
 */
@Slf4j
public class SyncExample2 {

    //修饰一个类
    public static void test1(int sign){
        synchronized (SyncExample2.class){
            for (int i = 0; i < 10; i++) {
                log.info("test - 线程{} - {}",sign,i);
            }
        }
        /*for (int i = 0; i < 10; i++) {
            log.info("test - {}",i);
        }*/
    }

    //修饰一个静态方法
    public synchronized void test2(int sign){
        for (int i = 0; i < 10; i++) {
            log.info("test2 - 线程{} - {}",sign,i);
        }
    }

    public static void main(String[] args) {
        SyncExample2 syncExample1 = new SyncExample2();
        SyncExample2 syncExample2 = new SyncExample2();
        //使用线程池使用两个线程执行方法
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncExample1.test1(1);
        });

        executorService.execute(() -> {
            syncExample1.test1(2);
        });
    }
}
