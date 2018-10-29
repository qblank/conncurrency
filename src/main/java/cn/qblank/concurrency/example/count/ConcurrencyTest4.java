package cn.qblank.concurrency.example.count;

import cn.qblank.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @date 2018/10/29
 * 并发量测试
 * volatile：适合做标识，不适合做写入操作
 */
@Slf4j
@NotThreadSafe
public class ConcurrencyTest4 {
    //请求综述
    public static int clientTotal = 5000;

    public static int threadToal = 200;

    public static volatile int count = 0;

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
        log.info("count:{}",count);
    }

    private static void add() {
        count++;
        //1.从内存取出count
        //2.+1
        //3.写回主存
    }


}
