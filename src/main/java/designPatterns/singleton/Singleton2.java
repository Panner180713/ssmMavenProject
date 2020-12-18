package designPatterns.singleton;

/**
 * 饿汉单例模式 在类加载的同时创建好一个静态对象以供使用，以后不再改变，所以天生是线程安全的
 * @Author chenshoukai
 * @Date 2020/12/18 9:44
 */
public class Singleton2 {

    private Singleton2(){}

    private static final Singleton2 INSTANCE = new Singleton2();

    public static Singleton2 getInstance(){
        return INSTANCE;
    }
}
