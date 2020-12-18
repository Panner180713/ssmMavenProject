package juc;

/**
 * volatile关键字：当多个线程操作共享数据时，可以保证内存中的数据可见。相较于synchronized，是一种较为轻量级的同步策略。
 * 注意：1.volatile不具备“互斥性”。互斥性：多个线程访问同一个共享资源，获得访问权限的线程会对共享资源加锁，其他线程必须等待锁释放之后才能进行访问。
 * 2.volatile不具备“原子性”。
 * @Author chenshoukai
 * @Date 2020/06/03 22:41
 */
public class TestVolatile {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();

        while (true){
            if (threadDemo.isFlag()){
                System.out.println("-----------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable{

    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setFlag(true);
        System.out.println("flag="+isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
