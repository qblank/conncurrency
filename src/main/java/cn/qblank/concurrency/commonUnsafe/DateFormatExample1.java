package cn.qblank.concurrency.commonUnsafe;

import cn.qblank.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @date 2018/10/31
 * 不安全的SimpleDateFormat写法
 */
@Slf4j
@NotThreadSafe
public class DateFormatExample1 {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    //请求综述
    public static int clientTotal = 5000;

    public static int threadTotal = 200;


    public static void main(String[] args) throws Exception{
        //定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void update() {
        try {
            sdf.parse("20180208");
        }catch (Exception e){
            log.error("parse exception,{}",e);
        }
    }

}
