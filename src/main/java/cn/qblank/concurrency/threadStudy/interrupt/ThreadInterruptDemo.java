package cn.qblank.concurrency.threadStudy.interrupt;

import lombok.extern.slf4j.Slf4j;

/**
 * @author evan_qb
 * @version 1.0
 * @date 2019/8/1 16:14
 */
@Slf4j
public class ThreadInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
//        interrupt1();
//        interrupt2();
        interrupt3();
    }

    private static void interrupt3() throws InterruptedException {
        //中断有阻塞状态(sleep/wait/joni)的线程，会产生一个InterruptedException异常，并将中断标志位清空，所以不会结束线程
        //所以在有阻塞状态(sleep/wait/joni)的while循环，除了应用interrupt()和isInterrupted()的结合外，还需要在外层catch这个异常，才能够停止线程
        Thread.sleep(200);
        System.out.println();
        log.info("在有阻塞状态(sleep/wait/joni)的while循环中应用interrupt()和isInterrupted()+catch");
        Thread thread3 = new Thread(() -> {
            try {
                // 如果当前线程没被中断，则一直进行
                while (!Thread.currentThread().isInterrupted()){
                    log.info("线程[{}]正在运行...",Thread.currentThread().getName());
                    Thread.sleep(5000);
                }
            }catch (InterruptedException e){
                log.info("线程[{}]停止运行",Thread.currentThread().getName());
            }
        });
        thread3.start();
        Thread.sleep(2000);
        thread3.interrupt();
    }


    private static void interrupt2() throws InterruptedException {
        Thread.sleep(200);
        System.out.println();
        log.info("在有阻塞状态(sleep/wait/joni)的while循环中应用interrupt()和isInterrupted()");
        Thread thread2 = new Thread(() -> {
            //如果当前线程没被中断，则一直进行
            while (!Thread.currentThread().isInterrupted()){
                log.info("线程[{}]正在运行",Thread.currentThread().getName());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("线程[{}]停止运行",Thread.currentThread().getName());
        });
        thread2.start();
        Thread.sleep(20);
        thread2.interrupt();
    }

    private static void interrupt1() throws InterruptedException {
        Thread.sleep(200);
        System.out.println();
        log.info("===========interrupt()、isInterrupted()可以结合，控制线程中的for(无阻塞状态)循环");
        new Thread(() -> {
            Thread thread = Thread.currentThread();
            for (int i = 0;i < 5 && !thread.isInterrupted();i++){
                log.info("线程[{}] is running,i = {},isInterrupted = {}",thread.getName(),i,thread.isInterrupted());
                if (i == 3){
                    thread.interrupt();
                    log.info("线程[{}] is isInterrupted,i = {},isInterrupted = {}",thread.getName(),i,thread.isInterrupted());
                }
            }
            log.info("线程[{}] 停止运行",Thread.currentThread().getName());
        }).start();
    }
}
