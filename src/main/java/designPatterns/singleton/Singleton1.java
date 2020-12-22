package designPatterns.singleton;

/**
 * 懒汉单例模式 第一次调用的时候实例化
 * @Author chenshoukai
 * @Date 2020/12/18 9:39
 */
public class Singleton1 {

    private Singleton1(){}

    private static Singleton1 instance;

    /**
     * @Description 缺陷：线程不安全，在并发情况下可能出现多个Singleton1实例
     * @Author: chenshoukai
     * @Date: 2020/12/18 9:49
     **/
    public static Singleton1 getInstance1(){
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }

    /**
     * @Description 解决了线程安全问题，但性能较差
     * @Author: chenshoukai
     * @Date: 2020/12/18 9:50
     **/
    public static synchronized Singleton1 getInstance2(){
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }

//    private static volatile Singleton1 instance;

    /**
     * @Description 双重检查锁定（DCL双检查锁机制）推荐
     * @Author: chenshoukai
     * @Date: 2020/12/18 10:06
     **/
    public static Singleton1 getInstance3(){
        if (instance == null) {
            synchronized (Singleton1.class){
                if (instance == null) {
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }

    /**
     * @Description 静态内部类 最推荐 既实现了线程安全，又避免了同步带来的性能影响
     * @Author: chenshoukai
     * @Date: 2020/12/18 10:09
     **/
    private static class LazyHolder{
        private static final Singleton1 SINGLETON_1 = new Singleton1();
    }

    public static final Singleton1 getInstance4(){
        return LazyHolder.SINGLETON_1;
    }
}
