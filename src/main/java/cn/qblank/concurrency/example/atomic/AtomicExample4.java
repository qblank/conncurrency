package cn.qblank.concurrency.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @date 2018/10/29
 * AtomicIntegerFieldUpdater介绍
 */
@Slf4j
public class AtomicExample4 {
    public static AtomicIntegerFieldUpdater<AtomicExample4> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample4.class,"count");

    @Getter
    private volatile int count = 100;

    public static void main(String[] args) {
        AtomicExample4 example4 = new AtomicExample4();
        if (updater.compareAndSet(example4,100,120)){
            log.info("update succ,{}",example4.getCount());
        }

        if (updater.compareAndSet(example4,100,120)){
            log.info("update succ,{}",example4.getCount());
        }else {
            log.info("update failed,{}",example4.getCount());
        }
    }
}
