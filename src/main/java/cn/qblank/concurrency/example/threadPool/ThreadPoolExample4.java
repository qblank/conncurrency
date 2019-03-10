package cn.qblank.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @date 2019/3/10 18:03
 */
@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        //延迟执行
        /*executor.schedule(() ->{
            log.info("schedule run");
        },3,TimeUnit.SECONDS); */

        //延迟一秒，间隔3秒执行任务
        executor.scheduleAtFixedRate(() -> {
            log.info("schedule run...");
        },1,3,TimeUnit.SECONDS);

        //timer
        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run");
            }
        },new Date(),5000);*/

        //executor.shutdown();
    }
}
