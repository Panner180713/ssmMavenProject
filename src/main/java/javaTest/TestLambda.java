package javaTest;

import function.MyFucntionalInterface;
import function.MyFunctionalInterface2;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import function.MyFucntionalInterface;
import function.MyFunctionalInterface2;
import javaBean.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import function.MyFucntionalInterface;
import function.MyFunctionalInterface2;
import javaBean.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author chenshoukai
 * @Date 2020/06/20 22:00
 * 一、lambda表达式的基本语法：java8中引入了一个新的操作符->，该操作符称为箭头操作符或lambda操作符。
 * 它将lambda表达式拆分为两部分，左侧：lambda表达式的参数列表；右侧：lambda表达式中所需执行的功能，即lambda体。
 * 语法格式一：无参数，无返回值
 * ()->System.out.println("test");
 * 语法格式二：有一个参数，无返回值
 * (x) -> System.out.println(x);
 * 语法格式三：若只有一个参数，小括号可以省略不写
 * x -> System.out.println(x);
 * 语法格式四：有两个参数，有返回值，并且有多条语句
 * Comparator<Integer> comparator = (x,y) -> {
 * System.out.println("多条语句");
 * return Integer.compare(x,y);
 * };
 * 语法格式五：有两个参数，有返回值，只有一条语句。return和大括号都可以省略不写。
 * (x,y) -> Integer.compare(x,y);
 * 语法格式六：lambda表达式的参数列表的数据类型可以省略不写。因为jvm编译器可以通过上下文推断出数据类型，即“类型推断”。
 * (Integer x,Integer y) -> Integer.compare(x,y);
 * 二、lambda表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口。可以使用注解@FunctionalInterface修饰。
 * 此注解可以检查当前接口是否是函数式接口。
 */
public class TestLambda {

    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world!");
            }
        };

        r1.run();

        System.out.println("--------------------------");

        Runnable r2 = () -> System.out.println("hello lambda!");

        r2.run();
    }

    @Test
    public void test2() {
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("hello lambda!");

        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer1.accept("hello world!");
    }

    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("多条语句");
            return Integer.compare(x, y);
        };

        System.out.println(comparator.compare(5, 6));
    }

    @Test
    public void test4() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

        System.out.println(comparator.compare(5, 6));
    }

    List<Student<String, Integer>> emps = Arrays.asList(
            new javaBean.Student<String, Integer>("john", 18, "jinan", 5),
            new javaBean.Student<String, Integer>("mike", 22, "qingdao", 1),
            new javaBean.Student<String, Integer>("smith", 27, "taian", 2)
    );

    @Test
    public void test5() {
        Collections.sort(emps, (e1, e2) -> {
            if(e1.getAge().equals(e2.getAge())){
                return e1.getName().compareTo(e2.getName());
            }else {
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        for (javaBean.Student<String, Integer> emp : emps) {
            System.out.println(emp);
        }
    }

    @Test
    public void test6(){
        String result = stringHandler("abcde",(string) -> string.toUpperCase());
        System.out.println(result);
    }

    @Test
    public void test7(){
        longHandler(100,200,(l1,l2) -> l1+l2);
        longHandler(100,200,(l1,l2) -> l1*l2);
    }

    private String stringHandler(String str, MyFucntionalInterface myFucntionalInterface){
        return myFucntionalInterface.getValue(str);
    }

    private void longHandler(long l1, long l2, MyFunctionalInterface2<Long,Long> mf){
        System.out.println(mf.getValue(l1,l2));
    }
}
