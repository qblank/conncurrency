package cn.qblank.concurrency.threadStudy.notify;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author evan_qb
 * @version 1.0
 * @date 2019/7/31 16:00
 */
@Slf4j
public class ThreadWaitDemo {

    /**
     * 现有菜品
     */
    private static final Queue<String> FOOD_QUEUE = new LinkedList<>();

    /**
     * 菜品工具类
     */
    public static class Foods {
        private static String[] foods = new String[]{"[鱼香肉丝]", "[水煮肉片]", "[地三鲜]", "[红烧肉]", "[干煸豆角]"};

        static String randomFood() {
            return foods[RandomUtils.nextInt(0,foods.length)];
        }
    }


    static class Kitchen implements Runnable {

        @Override
        public void run() {
            while (true){
                synchronized (FOOD_QUEUE) {
                    // 菜价满了，厨房不必再炒菜，等着餐厅来再炒菜
                    // 厨房的菜价能够存放菜品的最大值
                    int maxSize = 6;
                    if (maxSize == FOOD_QUEUE.size()){
                        try {
                            log.info("厨房菜架满了，厨房不必再炒菜，等着餐厅通知再炒菜，当前菜架：" + FOOD_QUEUE.toString());
                            FOOD_QUEUE.wait(111);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }else{
                        //炒菜
                        String food = Foods.randomFood();
                        FOOD_QUEUE.add(food);
                        try {
                            log.info("厨房炒了一个：" + food + "，厨房歇息2分钟。。。当前菜架：" + FOOD_QUEUE.toString());
                            Thread.sleep(2000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    /**
     * 餐厅：消费各种菜肴
     */
    static class Restaurant implements Runnable {

        @Override
        public void run() {
            while (true){
                synchronized (FOOD_QUEUE) {
                    // 如果生意太好，菜品供不应求，只能等待厨房做菜。。。
                    if (FOOD_QUEUE.size() == 0){
                        try {
                            log.info("餐厅：生意太好，菜品供不应求，只能等待厨房做菜。。。");
                            FOOD_QUEUE.wait(3000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }else if (FOOD_QUEUE.size() > 0) {
                        // 如果有菜，则消费菜品
                        // 当厨房的储备菜品所剩不多时，告知厨师开始炒菜
                        // 当厨房还剩几个菜时，继续炒菜
                        int minSize = 2;
                        if (FOOD_QUEUE.size() <= minSize){
                            FOOD_QUEUE.notify();
                            log.info("餐厅：厨房的储备菜品所剩不多，厨师可以开始干活了。。。");
                        }
                        // 消费菜品
                        String food = FOOD_QUEUE.poll();
                        try {
                            // 随机一定时间吃掉一道菜
                            Thread.sleep(RandomUtils.nextInt(1500,2500));
                            log.info("餐厅：刚刚消费了一道" + food + "...当前菜架" + FOOD_QUEUE.toString());
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException{
        //通过关键字synchronized和Object的方法wait()/notify()/notifyAll()实现线程等待与唤醒
        //通过object.wait()，使得对象线程进行入等待唤醒状态，并是否对象上的锁
        //通过object.notify()/object.notifyALL()，唤醒此对象上等待的线程，并获得对象上的锁
        //wait()/notify()/notifyAll()必须在synchronized中使用

        new Thread(new Kitchen()).start();
        // 先让厨房先多炒几个菜
        Thread.sleep(10000);
        // 餐厅开始消费
        new Thread(new Restaurant()).start();
    }


}
