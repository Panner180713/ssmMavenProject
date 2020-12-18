package juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Author chenshoukai
 * @Date 2020/06/11 8:47
 * Fork-join框架
 */
public class TestForkJoin {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask forkJoinTask = new ForkJoinTask(0L, 1000000000L);
        Long startTime = System.currentTimeMillis();
        Future<Long> result = pool.submit(forkJoinTask);
        try {
            Long sum = result.get();
            Long endTime = System.currentTimeMillis();
            System.out.println(sum);
            System.out.println("耗费的时间为:" + (endTime - startTime) + "毫秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }
    }
}

class ForkJoinTask extends RecursiveTask<Long> {

    /**
     * 临界值
     */
    private static final long THREAD_HOLD = 10000L;

    private long start;

    private long end;

    public ForkJoinTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if ((end - start) <= THREAD_HOLD) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            long middle = (start + end) / 2;
            ForkJoinTask left = new ForkJoinTask(start, middle);
            ForkJoinTask right = new ForkJoinTask(middle + 1, end);
            //执行子任务
            left.fork();
            right.fork();
            //获取子任务执行结果
            sum = left.join() + right.join();
        }
        return sum;
    }
}
