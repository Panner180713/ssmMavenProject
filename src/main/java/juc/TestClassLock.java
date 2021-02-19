package juc;

/**
 * @Author chenshoukai
 * @Date 2020/06/10 9:37
 */
public class TestClassLock {
    public static void main(String[] args) {
        ClassLock classLock = new ClassLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ClassLock.printOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                classLock.printTwo();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                classLock.printThree();
            }
        }).start();
    }
}

class ClassLock{
    public static synchronized void printOne(){//获取的(类)锁是ClassLock.class
        System.out.println("进入printOne");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public synchronized void printTwo(){//获取的(对象)锁是this
        System.out.println("进入printTwo");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("two");
    }

    public synchronized void printThree(){//获取的(对象)锁是this
        System.out.println("printThree");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("three");
    }
}
