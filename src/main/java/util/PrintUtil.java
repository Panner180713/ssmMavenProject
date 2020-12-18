package util;

/**
 * @Author chenshoukai
 * @Date 2020/06/17 16:11
 */
public class PrintUtil {

    /**
     * 静态方法无法访问类上定义的泛型，所以如果静态方法要使用泛型的话，必须将静态方法也定义成泛型方法
     * 只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     * @param list
     * @param <E>
     */
    public static <E> void print(E[] list){
        for (E e : list) {
            System.out.println(e);
        }
    }

    public static <T> void printMsg(T ... args){
        for (T arg : args) {
            System.out.println(arg);
        }
    }

    public static void main(String[] args) {
        printMsg("111",222,"aaaa","2323.4",55.55);
    }
}