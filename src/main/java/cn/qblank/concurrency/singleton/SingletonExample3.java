package cn.qblank.concurrency.singleton;

import cn.qblank.concurrency.annoations.NotRecommend;
import cn.qblank.concurrency.annoations.NotThreadSafe;
import cn.qblank.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @date 2018/10/30
 * (线程安全)懒汉单例模式
 * 不推荐
 */
@Slf4j
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    //私有化构造函数
    private SingletonExample3(){

    }
    //单例对象
    private static SingletonExample3 instance = null;

    //静态工厂方法
    public synchronized static SingletonExample3 getInstance(){
        if (instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
