package cn.qblank.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程创建方式
 * @version 1.0
 * @date 2019/3/10 9:42
 */
@Slf4j
public class ThreadExample3 implements Callable<String> {
    private int id;
    private static int count = 10;
    private final int time = count--;

    public ThreadExample3(int id) {
        this.id = id;
    }

    public static void main(String[] args) throws InterruptedException,ExecutionException{
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(executor.submit(new ThreadExample3(i)));
        }
        for (Future<String> fs:results){
            log.info("{}" ,fs.get());
        }
        executor.shutdown();
    }


    @Override
    public String call() throws Exception {
        TimeUnit.MILLISECONDS.sleep(100);
        return "result of taskWithResult:"  + id + ",time=" + time;
    }
}
