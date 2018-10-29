package cn.qblank.concurrency.example.atomic;

import cn.qblank.concurrency.annoations.NotThreadSafe;
import cn.qblank.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @date 2018/10/29
 * 并发量测试
 * 原子性测试 CAS、Unsafe.compareAndSwapInt
 */
@Slf4j
@ThreadSafe
public class AtomicExample1 {
    //请求综述
    public static int clientTotal = 5000;

    public static int threadToal = 200;
    //准确性高
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        //定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建信号量
        final Semaphore semaphore = new Semaphore(threadToal);
        //定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count.get());
    }

    private static void add() {
        count.incrementAndGet();
        //count.getAndIncrement();
    }


}
