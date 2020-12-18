package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author chenshoukai
 * @Date 2020/06/08 8:35
 */
public class TestProducerAndConsumerForLock {

    public static void main(String[] args) {
        Clerk02 clerk02 = new Clerk02();
        Producer02 producer02 = new Producer02(clerk02);
        Consumer02 consumer02 = new Consumer02(clerk02);

        new Thread(producer02,"生产者A").start();
        new Thread(consumer02,"消费者B").start();

        new Thread(producer02,"生产者C").start();
        new Thread(consumer02,"消费者D").start();
    }
}

class Producer02 implements Runnable{
    private Clerk02 clerk02;

    public Producer02(Clerk02 clerk) {
        this.clerk02 = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk02.get();
        }
    }
}

class Consumer02 implements Runnable{

    private Clerk02 clerk02;

    Consumer02(Clerk02 clerk02) {
        this.clerk02 = clerk02;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk02.sale();
        }
    }
}

class Clerk02{

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private int productionNum = 0;

    public void get(){
        lock.lock();
        try {
            while(productionNum >= 1){
                System.out.println("库存已满");
                try {
//                    this.wait();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"增加了库存："+ ++ productionNum);
//            this.notifyAll();
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void sale(){
        lock.lock();
        try {
            while(productionNum <= 0){
                System.out.println("缺货");
                try {
//                    this.wait();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"售出了商品："+  --productionNum);
//            this.notifyAll();
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
