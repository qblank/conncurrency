package cn.qblank.concurrency.example.aqs;

import cn.qblank.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 并发计数器
 * @version 1.0
 * @date 2019/3/9 17:07
 */
@Slf4j
@ThreadSafe
public class CountDownLatchExample1 {

    private static int threadCount = 100;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                }catch (Exception e){
                    log.error("错误:{}",e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        //等待计数器为0时，再进行
        //等待100毫秒,超过时间则执行下面代码
        countDownLatch.await(100, TimeUnit.MILLISECONDS);
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNum);
    }
}
