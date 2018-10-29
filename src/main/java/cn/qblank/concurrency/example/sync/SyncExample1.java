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
public class SyncExample1 {

    //修饰一个代码块
    public void test1(int sign){
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                log.info("test - 线程{} - {}",sign,i);
            }
        }
        /*for (int i = 0; i < 10; i++) {
            log.info("test - {}",i);
        }*/
    }

    //修饰方法
    public synchronized void test2(int sign){
        for (int i = 0; i < 10; i++) {
            log.info("test2 - 线程{} - {}",sign,i);
        }
    }

    public static void main(String[] args) {
        SyncExample1 syncExample1 = new SyncExample1();
        SyncExample1 syncExample2 = new SyncExample1();
        //使用线程池使用两个线程执行方法
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncExample1.test2(1);
        });

        executorService.execute(() -> {
            syncExample2.test2(2);
        });
    }
}
