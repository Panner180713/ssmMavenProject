package javaTest;

import javaBean.Employee;
import org.junit.Test;

import java.util.function.BiPredicate;
import java.util.function.Function;

import javaBean.Employee;
import org.junit.Test;

import java.util.function.BiPredicate;

/**
 * @Author chenshoukai
 * @Date 2020/06/26 16:18
 * 一、方法引用：若lambda体中的方法已经有方法实现了，我们可以使用“方法引用”。
 * 主要有三种语法格式：
 *
 * 对象::实例方法名
 *
 * 类::静态方法名
 *
 * 类::实例方法名
 *
 * 注意：
 * 1.lambda体中调用方法的参数列表与返回值类型要与函数式接口中抽象方法的参数列表与返回值类型保持一致
 * 2.若lambda参数列表的第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，可以使用 ClassName :: MethodName
 *
 * 二、构造器引用
 *
 * ClassName::new
 *
 * 注意：
 * 需要调用的构造器的参数列表要与函数式接口抽象方法的参数列表保持一致。
 *
 * 三、数组引用
 *
 * Type[]::new
 *
 */
public class TestMethodRef {

    @Test
    public void test2(){
        Function<Integer, Employee> function = (x) -> new Employee(x);
        Employee employee = function.apply(1);
        System.out.println(employee);

        Function<Integer, Employee> function2 = Employee::new;
        Employee employee2 = function2.apply(2);
        System.out.println(employee2);
    }


    @Test
    public void test1(){
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        //equals方法是个实例方法，正常应由对象来调用，可下面调用方式直接用类名调用，语法详情见注意二。
        BiPredicate<String, String > biPredicate1 = String :: equals;
    }
}
