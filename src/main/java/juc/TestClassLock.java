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
    }
}

class ClassLock{
    public static synchronized void printOne(){//获取类锁的是ClassLock.class
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public synchronized void printTwo(){//获取对象锁的是this
        System.out.println("two");
    }
}
