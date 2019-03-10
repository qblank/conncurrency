package cn.qblank.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition类讲解
 * @version 1.0
 * @date 2019/3/9 22:30
 */
@Slf4j
public class LockExample6 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        new Thread(() -> {
           try {
               reentrantLock.lock();
               log.info("wait signal"); //1.等待信号
               condition.await();
           }catch (InterruptedException e){
               log.error("{}",e);
           }
           log.info("get signal");  //4
           reentrantLock.unlock();
        }).start();

        new Thread(() -> {
           reentrantLock.lock();
           log.info("get lock");    //2
           try {
               Thread.sleep(3000);
           }catch (InterruptedException e){
               log.error("{}",e);
           }
           condition.signalAll();
           log.info("send signal ~ ");  //  3
           reentrantLock.unlock();
        }).start();
    }
}
