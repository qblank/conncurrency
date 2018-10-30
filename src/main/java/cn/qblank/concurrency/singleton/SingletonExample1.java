package cn.qblank.concurrency.singleton;

import cn.qblank.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @date 2018/10/30
 * 懒汉单例模式
 */
@Slf4j
@NotThreadSafe
public class SingletonExample1 {
    //私有化构造函数
    private SingletonExample1(){

    }
    //单例对象
    private static SingletonExample1 instance = null;

    //静态工厂方法
    public static SingletonExample1 getInstance(){
        if (instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
