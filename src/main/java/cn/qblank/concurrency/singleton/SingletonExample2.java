package cn.qblank.concurrency.singleton;

import cn.qblank.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @date 2018/10/30
 * 饿汉单例模式
 */
@Slf4j
@ThreadSafe
public class SingletonExample2 {
    //私有化构造函数
    private SingletonExample2(){

    }
    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态工厂方法
    public static SingletonExample2 getInstance(){
       return instance;
    }
}
