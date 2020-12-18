package juc;

import java.util.Iterator;

/**
 * @Author chenshoukai
 * @Date 2020/06/07 19:21
 * java.util.concurrent.CopyOnWriteArrayList会在每次写入数据之前先复制一个新的list，然后再写入。
 * 所以添加操作多的时候性能低。并发迭代操作多时可以考虑使用。
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        for (int i = 0; i < 10; i++) {
            new Thread(copyOnWriteArrayList).start();
        }
    }
}

class CopyOnWriteArrayList implements Runnable{

//    private static List<String> list = new ArrayList<>();

//    private static List<String> list = Collections.synchronizedList(new ArrayList<>());

    private static java.util.concurrent.CopyOnWriteArrayList list = new java.util.concurrent.CopyOnWriteArrayList();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
            list.add("AA");
        }
    }
}
