package javaTest.lambda;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @Author chenshoukai
 * @Date 2020/06/26 11:06
 * 由于lambda表达式需要函数式接口的支持，所以java8内置了四大核心函数式接口。
 * 这四个是最基本的，还有一些其他的函数式接口，使用时自己找。
 * Consumer<T> 消费型接口
 * void accept(T t);
 *
 * Supplier<T> 供给型接口
 * T get();
 *
 * Function<T,R> 函数型接口
 * R apply(T t);
 *
 * Predicate<T> 断言型接口
 * boolean test(T t);
 */
public class TestLambda2 {

    @Test
    public void test1() {
        happy(10000, (money) -> System.out.println("消费了" + money + "元"));
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }


}
