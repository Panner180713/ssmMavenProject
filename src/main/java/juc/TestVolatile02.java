package juc;

/**
 * @Author chenshoukai
 * @Date 2020/06/04 10:37
 */
public class TestVolatile02 {
    public static void main(String[] args) {
        ThreadDemo02 threadDemo02 = new ThreadDemo02();
        Thread thread01 = new Thread(threadDemo02, "线程1");
        Thread thread02 = new Thread(threadDemo02, "线程2");
        Thread thread03 = new Thread(threadDemo02, "线程3");
        thread01.start();
        thread02.start();
        thread03.start();
    }
}

class ThreadDemo02 implements Runnable {

    private volatile int ticket = 5;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (this) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票,余票" + --ticket+"张");
                }
            }
        }
    }
}
