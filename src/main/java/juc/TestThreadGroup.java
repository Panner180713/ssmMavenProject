package juc;

/**
 * 线程组的作用：批量管理线程，有效的对线程进行组织。
 * @Author chenshoukai
 * @Date 2021/02/24 15:40
 */
public class TestThreadGroup {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("线程组");

        EndlessLoopThread endlessLoopThread01 = new EndlessLoopThread(threadGroup,"线程1");
        EndlessLoopThread endlessLoopThread02 = new EndlessLoopThread(threadGroup,"线程2");
        EndlessLoopThread endlessLoopThread03 = new EndlessLoopThread(threadGroup,"线程3");

        endlessLoopThread01.start();
        endlessLoopThread02.start();
        endlessLoopThread03.start();

        Thread.sleep(3000);

        System.out.println("线程组调用interrupt()方法");
        threadGroup.interrupt();
    }
}

class EndlessLoopThread extends Thread{

    public EndlessLoopThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始死循环");
        while(!Thread.currentThread().isInterrupted()){}
        System.out.println(Thread.currentThread().getName() + "结束死循环");
    }
}
