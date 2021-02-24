package juc;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列：生产者和消费者通过队列共享数据。某些情况下，生产者/消费者线程会挂起(即阻塞)，一旦条件满足，被挂起的线程又会自动唤醒。
 * 使用BlockingQueue，不需要关心什么时候需要阻塞线程，什么时候需要唤醒，这些内容BlockingQueue自动做好了。
 * https://www.cnblogs.com/szlbm/p/5588518.html
 * @Author chenshoukai
 * @Date 2021/02/24 14:28
 */
public class TestProducerAndConsumerForQueue {

    public static void main(String[] args) {
        Clerk03 clerk03 = new Clerk03();

        Producer03 producer03 = new Producer03(clerk03);
        Consumer03 consumer03 = new Consumer03(clerk03);

        new Thread(producer03).start();
        new Thread(consumer03).start();
    }
}

class Producer03 implements Runnable{

    private Clerk03 clerk03;

    Producer03(Clerk03 clerk03) {
        this.clerk03 = clerk03;
    }

    @Override
    public void run() {
        clerk03.get();
    }
}

class Consumer03 implements Runnable{

    private Clerk03 clerk03;

    Consumer03(Clerk03 clerk03) {
        this.clerk03 = clerk03;
    }

    @Override
    public void run() {
        clerk03.sale();
    }
}

class Clerk03{
    private ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

    private int i = 0;

    public void get(){
        while (true){
            try {
                System.out.println("生产了 " + ++i);
                arrayBlockingQueue.put(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void sale(){
        while (true){
            try {
                System.out.println("消费了 " + arrayBlockingQueue.take());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
