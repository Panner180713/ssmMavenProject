package juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author chenshoukai
 * @Date 2020/06/10 9:22
 * 读写、写写操作互斥，读读操作不互斥
 */
public class TestReadAndWriteLock {

    public static void main(String[] args) {
        ReadAndWrite readAndWrite = new ReadAndWrite();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readAndWrite.write();
                }
            }).start();
        }

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readAndWrite.read();
                }
            }).start();
        }
    }
}

class ReadAndWrite{
    private int number = 0;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void read(){
        lock.readLock().lock();
        System.out.println("进入read方法");
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"读取number的值为:"+number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }

    public void write(){
        lock.writeLock().lock();
        System.out.println("进入write方法");
        try {
            Thread.sleep(500);
            this.number = ++number;
            System.out.println(Thread.currentThread().getName()+"设置number的值为:"+number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
}