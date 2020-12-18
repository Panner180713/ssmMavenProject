package juc;

/**
 * @Author chenshoukai
 * @Date 2020/06/08 8:35
 */
public class TestProducerAndConsumer {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(producer,"生产者A").start();
        new Thread(consumer,"消费者B").start();

        new Thread(producer,"生产者C").start();
        new Thread(consumer,"消费者D").start();
    }
}

class Producer implements Runnable{
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.get();
        }
    }
}

class Consumer implements Runnable{

    private Clerk clerk;

    Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}

class Clerk{

    private int productionNum = 0;

    public synchronized void get(){
        while(productionNum >= 1){
            System.out.println("库存已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"增加了库存："+ ++productionNum);
        this.notifyAll();
    }

    public synchronized void sale(){
        while(productionNum <= 0){
            System.out.println("缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"售出了商品："+  --productionNum);
        this.notifyAll();
    }
}
