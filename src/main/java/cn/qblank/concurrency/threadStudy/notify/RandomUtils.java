package cn.qblank.concurrency.threadStudy.notify;

import java.util.Random;

/**
 * @author evan_qb
 * @version 1.0
 * @date 2019/7/31 16:03
 */
public class RandomUtils {
    public static int nextInt(int start,int range){
        Random random = new Random();
        return random.nextInt(range) + start;
    }
}
