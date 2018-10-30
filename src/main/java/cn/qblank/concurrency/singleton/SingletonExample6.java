package cn.qblank.concurrency.singleton;

import cn.qblank.concurrency.annoations.NotThreadSafe;
import cn.qblank.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @date 2018/10/30
 * 饿汉单例模式
 */
@Slf4j
@ThreadSafe
public class SingletonExample6 {
    //私有化构造函数
    private SingletonExample6(){

    }

    //单例对象
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    //静态工厂方法
    public static SingletonExample6 getInstance(){
       return instance;
    }

    public static void main(String[] args) {
        System.out.println(SingletonExample6.getInstance().hashCode());
        System.out.println(SingletonExample6.getInstance().hashCode());
    }
}
