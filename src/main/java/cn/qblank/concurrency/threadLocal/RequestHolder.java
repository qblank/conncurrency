package cn.qblank.concurrency.threadLocal;


/**
 * @date 2018/10/31
 */
public class RequestHolder {

    private static final ThreadLocal<Long> threadIdHolder = new ThreadLocal<>();


    public static void add(Long threadId){
        threadIdHolder.set(threadId);
    }

    public static Long getId(){
        return threadIdHolder.get();
    }

    public static void remove(){
        threadIdHolder.remove();
    }

}
