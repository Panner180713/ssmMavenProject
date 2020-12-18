package juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一、i++的原子性问题：i++的实际操作分为三个步骤，“读 改 写”
 * int temp = i;
 * temp = i + 1;
 * i = temp;
 * 二、原子变量
 * jdk1.5之后java.util.concurrent.atomic包下提供了常用的原子变量
 * 1.原子变量内部使用了volatile修饰符保证可见性
 * 2.使用CAS算法保证数据的原子性
 *
 */
public class TestAtomic {

    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(atomicDemo).start();
        }
    }

}

class AtomicDemo implements Runnable{

    private AtomicInteger serialNumber = new AtomicInteger(0);

    @Override
    public void run() {
        System.out.println(getSerialNumber());
    }

    private int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }
}
