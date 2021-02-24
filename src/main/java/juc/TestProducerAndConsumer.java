package juc;

/**
 * 生产者/消费者模型的优秀之处：
 * 1.解耦。因为多了一个缓冲区，所以生产者和消费者并不直接相互调用。这样生产者或消费者的代码发生变化，都不会对对方产生影响。
 * 这样其实就是把生产者和消费者的强耦合解开，变为了生产者和缓冲区/消费者和缓冲区的弱耦合。
 * 2.通过平衡生产者和消费者的处理能力来提高整体处理数据的速度。如果消费者直接从生产者这里拿数据，而生产者生产的速度很慢，但消费者消费的速度很快，
 * 那消费者就得占用cpu的时间片白白等在那里。有了生产者/消费者模型，生产者和消费者就是两个独立的并发体，使生产者和消费者的处理能力达到一个动态的平衡。
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
        while(productionNum >= 10){
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
