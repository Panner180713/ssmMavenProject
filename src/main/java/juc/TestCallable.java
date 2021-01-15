package juc;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author chenshoukai
 * @Date 2020/06/07 20:17
 * 一、创建执行线程的方式三：实现Callable接口。相较于实现Runnable接口，方法可以有返回值，并且可以抛出异常。
 * 二、执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果。FutureTask是Future接口的实现类。
 */
public class TestCallable {
    public static void main(String[] args) {
        CallableDemo callableDemo = new CallableDemo();
        FutureTask<Integer> futureTask = new FutureTask<>(callableDemo);
        new Thread(futureTask).start();
        System.out.println(">>>>>>>>>>>>>");
        try {
            //获取线程的执行结果，线程执行完毕之前阻塞。类似于闭锁。
            Integer result = futureTask.get();
            System.out.println(result);
            System.out.println("-------------");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

class CallableDemo implements Callable<Integer>{

    @Override
    public Integer call() throws IOException {
        int sum = 0;

        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        if(sum > 10000){
            throw new IOException("test IOException");
        }
        return sum;
    }
}