package cn.qblank.concurrency.immutable;

import cn.qblank.concurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @date 2018/10/31
 * final关键字
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;

    private final static String b = "2";

    private final static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
        // a = 2;
        // b = "1"
        // map = Maps.newHashMap();
        map.put(1,3);
        //线程不安全
        log.info("{}",map.get(1));
    }

    /*private void test(final int a){
        a = 1;
    }*/
}
