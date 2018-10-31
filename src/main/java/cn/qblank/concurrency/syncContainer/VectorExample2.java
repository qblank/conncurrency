package cn.qblank.concurrency.syncContainer;

import cn.qblank.concurrency.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * @date 2018/10/31
 * 同步容器线程安全问题Stack和Vector类似
 */
@NotThreadSafe
public class VectorExample2 {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true){
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            //移除元素
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };

            //查询元素
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
