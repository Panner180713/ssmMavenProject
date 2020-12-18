package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author chenshoukai
 * @Date 2020/06/09 8:40
 */
public class TestABCAlternate {

    public static void main(String[] args) {
        AlternateDemo alternateDemo = new AlternateDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    alternateDemo.printA(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    alternateDemo.printB(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    alternateDemo.printC(i);
                }
            }
        }).start();
    }
}

class AlternateDemo {

    private int printNumber = 1;

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA(int count) {
        System.out.println("进入printA");
        lock.lock();
        try {
            if (printNumber != 1) {
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("A" + "\t" + count);
            printNumber = 2;
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }

    public void printB(int count) {
        System.out.println("进入printB");
        lock.lock();
        try {
            if (printNumber != 2) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("B" + "\t" + count);
            printNumber = 3;
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }

    public void printC(int count) {
        System.out.println("进入printC");
        lock.lock();
        try {
            if (printNumber != 3) {
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C" + "\t" + count);
            System.out.println("--------------------");
            printNumber = 1;
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }
}
