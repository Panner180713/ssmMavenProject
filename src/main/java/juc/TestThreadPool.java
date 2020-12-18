package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author chenshoukai
 * @Date 2020/06/10 9:54
 */
public class TestThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        ThreadTask threadTask = new ThreadTask();
        for (int i = 0; i < 10; i++) {
            pool.submit(threadTask);
        }
        //线程池中的线程执行完毕之后关闭线程池
        pool.shutdown();
    }
}

class ThreadTask implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
        }
    }
}
