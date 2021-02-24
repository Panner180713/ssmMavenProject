package juc;

/**
 * @Author chenshoukai
 * @Date 2021/02/20 9:43
 */
public class TestDeadLock {

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();

        Thread thread1 = new Thread(new LeftThread(deadLock));
        Thread thread2 = new Thread(new RightThread(deadLock));

        thread1.start();
        thread2.start();
    }
}

class DeadLock{

    private final Object left = new Object();

    private final Object right = new Object();

    public void leftRight() throws InterruptedException {
        synchronized (left){
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "持有left对象锁，请求right对象锁...");
            synchronized (right){
                System.out.println("leftRight end...");
            }
        }
    }

    public void rightLeft() throws InterruptedException {
        synchronized (right){
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "持有right对象锁，请求left对象锁...");
            synchronized (left){
                System.out.println("rightLeft end...");
            }
        }
    }
}

class LeftThread implements Runnable{

    private DeadLock deadLock;

    public LeftThread(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    @Override
    public void run() {
        try {
            deadLock.leftRight();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class RightThread implements Runnable{

    private DeadLock deadLock;

    public RightThread(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    @Override
    public void run() {
        try {
            deadLock.rightLeft();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
