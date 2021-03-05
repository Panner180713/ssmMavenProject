package juc;

/**
 * @Author chenshoukai
 * @Date 2021/03/04 16:43
 */
public class TestThreadLocal {

    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>(){
        /**
         * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后，再调用get方法，返回此方法值
         */
        @Override
        protected Object initialValue()
        {
            System.out.println("调用get方法时，当前线程共享变量没有设置，调用initialValue获取默认值！");
            return null;
        }
    };

    static class MyIntegerTask implements Runnable{
        private Integer value = 0;

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                TestThreadLocal.threadLocal.set(value++);
                System.out.println(Thread.currentThread().getName() + "-----" + TestThreadLocal.threadLocal.get());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    public static void main(String[] args) {
        MyIntegerTask myIntegerTask = new MyIntegerTask();
        new Thread(myIntegerTask).start();
        new Thread(myIntegerTask).start();
        new Thread(myIntegerTask).start();
        new Thread(myIntegerTask).start();
    }
}
