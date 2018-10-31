package cn.qblank.concurrency.commonUnsafe;

import cn.qblank.concurrency.annoations.NotThreadSafe;
import cn.qblank.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @date 2018/10/31
 * StringBuffer
 */
@Slf4j
@ThreadSafe
public class StringExample2 {
    //请求综述
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static StringBuffer sb = new StringBuffer();

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
        log.info("size:{}",sb.length());
    }

    private static void update() {
        sb.append("1");
    }
}
