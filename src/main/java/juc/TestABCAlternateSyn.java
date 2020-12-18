package juc;

/**
 * @Author chenshoukai
 * @Date 2020/06/09 8:40
 */
public class TestABCAlternateSyn {

    public static void main(String[] args) {
        AlternateSynDemo alternateSynDemo = new AlternateSynDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    alternateSynDemo.printA(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    alternateSynDemo.printB(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    alternateSynDemo.printC(i);
                }
            }
        }).start();
    }
}

class AlternateSynDemo {

    private int printNumber = 1;

    public synchronized void printA(int count) {
        System.out.println("进入printA");
        while (printNumber != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("A" + "\t" + count);
        printNumber = 2;
        this.notifyAll();
    }

    public synchronized void printB(int count) {
        System.out.println("进入printB");
        while (printNumber != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("B" + "\t" + count);
        printNumber = 3;
        this.notifyAll();
    }

    public synchronized void printC(int count) {
        System.out.println("进入printC");
        while (printNumber != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("C" + "\t" + count);
        System.out.println("--------------------");
        printNumber = 1;
        this.notifyAll();
    }
}
