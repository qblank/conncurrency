package cn.qblank.concurrency.syncContainer;

import cn.qblank.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @date 2018/10/31
 * HashTable
 */
@Slf4j
@ThreadSafe
public class CollectionsExample3 {

    //请求综述
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    private static Map<Integer,Integer> map = Collections.synchronizedMap(Maps.newHashMap());


    public static void main(String[] args) throws Exception{
        //定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}",map.size());
    }

    private static void update(int i) {
        map.put(i,i);
    }
}
