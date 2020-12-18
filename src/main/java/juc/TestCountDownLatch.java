package juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Author chenshoukai
 * @Date 2020/06/07 19:46
 * CountDownLatch闭锁：在完成某些运算时，只有当其他所有线程的运算全部完成，当前运算才继续执行。
 * 例：当5个打印50000以内的偶数的线程全部执行完成之后，计算执行时间。
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(countDownLatchDemo).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费的时间：" + (end - start) + "毫秒");
    }
}

class CountDownLatchDemo implements Runnable {

    private CountDownLatch latch;

    public CountDownLatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 50000; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
        } finally {
            synchronized (this){
                latch.countDown();
            }
        }
    }
}