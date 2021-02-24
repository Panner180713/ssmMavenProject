package juc;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器Timer：
 * 1.启动一个timer就是启动一个新线程，但是这个新线程默认并不是一个守护线程。若要运行完就让线程停止的话，就设置timer为守护线程。
 * 2.也可以给timer设置多个任务。任务是以队列的方式顺序执行的，所以执行时间有可能和预期时间不一致。
 * 因为前面的任务可能耗时过长，导致后面任务的运行时间被延迟。
 * 3.若设置的任务执行时间早于当前时间，则立即执行。
 * 4.使用timer.cancel()方法取消任务。
 * @Author chenshoukai
 * @Date 2021/02/24 17:02
 */
public class TestTimer {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        FutureTask futureTask = new FutureTask();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,3);
        System.out.println("已设置定时任务");
//        timer.schedule(futureTask,calendar.getTime(),1000);
        timer.schedule(futureTask,3000,1000);

//        Thread.sleep(3000);
    }
}

class FutureTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("定时任务执行了，执行时间：" + new Date());
    }
}
