package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用于解决多线程安全问题的方式
 * jdk1.5之前
 * synchronized隐式锁
 * 1.同步代码块
 * 2.同步方法
 *
 * jdk1.5之后
 * 3.同步锁Lock
 * 注意：是一个显式锁，通过lock()方法上锁，通过unlock()方法释放锁
 */
public class TestLock {

    public static void executeTask(){
        LockDemo lockDemo = new LockDemo();

        new Thread(lockDemo,"1号窗口").start();
        new Thread(lockDemo,"2号窗口").start();
        new Thread(lockDemo,"3号窗口").start();
    }

    public static void main(String[] args) {
        executeTask();

        System.out.println("task over");
    }
}

class LockDemo implements Runnable {

    private int ticketNum = 50;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "进入run()方法");
        while (true) {
            lock.lock();
            try {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ticketNum > 0) {
                    System.out.println(Thread.currentThread().getName() + "完成售票，余票数为：" + --ticketNum);
                }else {
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
