package cn.qblank.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.concurrent.*;

/**
 * 多线程执行累加
 * @version 1.0
 * @date 2019/3/10 10:48
 */
@Slf4j
public class ForkJoinTaskExample {
    private static final Integer MAX = 200;

    static class MyForkJoinTask extends RecursiveTask<Integer>{

        // 子任务开始计算的值
        private Integer startValue;
        // 子任务结束计算的值
        private Integer endValue;

        public MyForkJoinTask(Integer startValue, Integer endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        protected Integer compute() {
            // 如果条件成立，说明这个任务所需要计算的数值分为足够小了
            // 可以进行计算了
            if (endValue - startValue < MAX){
                log.info("开始计算的部分：startValue={};endValue={}",startValue,endValue);
                Integer totalValue = 0;
                for(int index = this.startValue;index <= this.endValue;index++){
                    totalValue += index;
                }
                return totalValue;
            }else{
                MyForkJoinTask subTask1 = new MyForkJoinTask(startValue, (startValue + endValue) / 2);
                subTask1.fork();
                MyForkJoinTask subTask2 = new MyForkJoinTask((startValue + endValue) / 2 + 1, endValue);
                subTask2.fork();
                //合并子线程执行结果
                return subTask1.join() + subTask2.join();
            }
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //这是Fork/Join框架的线程池

        //并行执行累加 1 ~ 1001
        accumulationMulti();    //501501
        //单线程累加
        //accumulationSingle();   //501501

        long endTime = System.currentTimeMillis();
        log.info("spend {} millSeconds",endTime - startTime);
    }

    /**
     * 多线程累加
     */
    private static void accumulationMulti() {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> taskFuture = pool.submit(new MyForkJoinTask(1, 1001));
        try {
            Integer result = taskFuture.get();
            log.info("result:{}",result);
        }catch (InterruptedException | ExecutionException e){
            log.error("e:{}",e);
        }
    }

    /**
     * 单线程累加
     */
    private static void accumulationSingle(){
        int sum = 0;
        for (int i = 1; i <= 1001; i++) {
            sum += i;
        }
        log.info("result:{}",sum);
    }
}
