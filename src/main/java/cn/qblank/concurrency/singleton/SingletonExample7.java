package cn.qblank.concurrency.singleton;

import cn.qblank.concurrency.annoations.Recommend;
import cn.qblank.concurrency.annoations.ThreadSafe;

/**
 * @date 2018/10/30
 * 枚举模式的单例模式
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {
    private SingletonExample7(){}

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        //JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}
