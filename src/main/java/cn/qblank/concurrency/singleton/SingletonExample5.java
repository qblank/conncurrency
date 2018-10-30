package cn.qblank.concurrency.singleton;

import cn.qblank.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @date 2018/10/30
 * (线程安全)懒汉单例模式 - 双重同步锁单例模式
 * 防止指令重排volatile
 */
@Slf4j
@ThreadSafe
public class SingletonExample5 {
    //私有化构造函数
    private SingletonExample5(){

    }
    //指令重排问题:
    //1.分配内存空间
    //2.初始化对象
    //3.instance = memory设置instance指向刚分配的内存

    //指令重排
    //1.分配内存空间
    //3.instance = memory设置instance指向刚分配的内存
    //2.初始化对象

    //单例对象
    private static volatile SingletonExample5 instance = null;

    //静态工厂方法
    public static SingletonExample5 getInstance(){
        if (instance == null){  //双重检测机制            //B
            synchronized (SingletonExample5.class) {    //同步锁
                if (instance == null){
                    instance = new SingletonExample5();     //A
                }
            }
        }
        return instance;
    }
}
