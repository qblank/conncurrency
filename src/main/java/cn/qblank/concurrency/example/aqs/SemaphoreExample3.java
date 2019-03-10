package cn.qblank.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 并发计数器
 * @version 1.0
 * @date 2019/3/9 17:07
 */
@Slf4j
public class SemaphoreExample3 {

    private static int threadCount = 20;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    //等待1秒
                    if (semaphore.tryAcquire(5,TimeUnit.SECONDS)){    //尝试获取一个许可
                        test(threadNum);
                        semaphore.release();    //释放一个许可
                    }
                }catch (Exception e){
                    log.error("错误:{}",e);
                }
            });
        }
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{}",threadNum);
    }
}
