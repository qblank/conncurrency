package cn.qblank.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程创建方式
 * @version 1.0
 * @date 2019/3/10 9:42
 */
@Slf4j
public class ThreadExample1 extends Thread{
    public static void main(String[] args) {
        new Thread(new ThreadExample1(),"线程1").start();
        new Thread(new ThreadExample1(),"线程2").start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            log.error("{}",e);
        }
        for (int i = 0; i < 10; i++) {
            log.info("{}",i);
        }
    }
}
