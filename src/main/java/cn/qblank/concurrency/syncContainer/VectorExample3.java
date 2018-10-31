package cn.qblank.concurrency.syncContainer;

import cn.qblank.concurrency.annoations.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;

/**
 * @date 2018/10/31
 * Vector测试
 * 不同遍历方法
 */
@NotThreadSafe
public class VectorExample3 {
    /**
     * foreach遍历:不要再遍历时进行更新操作
     * @param v1
     */
    private static void test1(Vector<Integer> v1){
        for(Integer i: v1){
            if (i.equals(3)){
                v1.remove(i);
            }
        }
    }

    /**
     * 迭代器:不要再遍历时进行更新操作
     * @param v1
     */
    private static void test2(Vector<Integer> v1){
        Iterator<Integer> it = v1.iterator();
        while (it.hasNext()){
            Integer i = it.next();
            if (i.equals(3)){
                v1.remove(i);
            }
        }
    }

    /**
     * for循环
     * @param v1
     */
    private static void test3(Vector<Integer> v1){
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i).equals(3)){
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test1(vector);  //java.util.ConcurrentModificationException
        test2(vector);  //java.util.ConcurrentModificationException
        test3(vector);  //success
    }
}
