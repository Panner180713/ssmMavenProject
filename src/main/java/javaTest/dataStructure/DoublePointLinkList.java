package javaTest.dataStructure;

/**
 * 双端链表
 * @Author chenshoukai
 * @Date 2020/09/05 14:11
 */
public class DoublePointLinkList {

    /**
     * 头节点
     */
    private Node head;

    /**
     * 尾节点
     */
    private Node tail;

    /**
     * 链表长度
     */
    private int size;

    private static class Node{

        private Object data;

        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    /**
     * 增加头节点
     */
    public void addHead(Object object){
        Node newHead = new Node(object);
        if(size == 0){
            head = newHead;
            tail = newHead;
        }else{
            newHead.next = head;
            head = newHead;
        }
        size++;
    }

    /**
     * 增加尾节点
     * @param object
     */
    public void addTail(Object object){
        Node newTail = new Node(object);
        if(size == 0){
            head = newTail;
            tail = newTail;
        }else{
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    /**
     * 删除头节点
     * @return
     */
    public boolean deleteHead(){
        if(size == 0){
            return false;
        }else if(size == 1){
            head = null;
            tail = null;
        }else{
            head = head.next;
        }
        size--;
        return true;
    }

    /**
     * 判空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 获取链表长度
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 遍历链表
     */
    public void display(){
        Node current = head;
        while(current != null && current.data != null){
            System.out.println(current.data.toString());
            current = current.next;
        }
    }

    public static void main(String[] args) {
        DoublePointLinkList doublePointLinkList = new DoublePointLinkList();
        doublePointLinkList.addHead("D");
        doublePointLinkList.addHead("C");
        doublePointLinkList.addHead("B");
        doublePointLinkList.addHead("A");
        doublePointLinkList.addTail("B");
        doublePointLinkList.display();
    }
}
