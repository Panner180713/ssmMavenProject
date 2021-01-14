package juc;

/**
 * 模拟CAS算法
 * CAS（Compare-And-Swap）算法保证数据操作的原子性。
 * CAS 算法是硬件对于并发操作共享数据的支持。
 * CAS 包含了三个操作数：
 * 　　内存值 V
 * 　　预估值 A
 * 　　更新值 B
 * 当且仅当 V == A 时，V 将被赋值为 B，否则循环着不断进行判断 V 与 A 是否相等。
 */
public class TestCompareAndSwap {
    public static void main(String[] args) {
        final CompareAndSwap compareAndSwap = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean result = false;
                    while(!result){
                        int expectedValue = compareAndSwap.getValue();
                        result = compareAndSwap.compareAndSet(expectedValue, (int) (Math.random()*101));
                    }
                    System.out.println(result);
                }
            }).start();
        }
    }
}

class CompareAndSwap{
    private int value;

    public int getValue() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return value;
    }

    private int compareAndSwap(int expectedValue, int newValue){
        int oldValue = this.value;
        if(oldValue == expectedValue){
            this.value = newValue;
        }
        return oldValue;
    }

    boolean compareAndSet(int expectedValue, int newValue){
        return expectedValue == compareAndSwap(expectedValue,newValue);
    }
}
