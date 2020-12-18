package javaTest.dataStructure;

/**
 * 单向链表
 * @Author chenshoukai
 * @Date 2020/09/04 20:58
 */
public class SingleLinkedList {

    /**
     * 链表长度
     */
    private int size;

    /**
     * 链表头节点
     */
    private Node head;

    private static class Node{
        /**
         * 节点数据
         */
        private Object data;

        /**
         * 下一个节点的引用
         */
        private Node next;

        private Node(Object data) {
            this.data = data;
        }
    }

    /**
     * 新增节点
     * @param object
     * @return
     */
    public Node addHead(Object object){
        Node newHead = new Node(object);
        if(null == head){
            head = newHead;
        }else{
            newHead.next = head;
            head = newHead;
        }
        size++;
        return newHead;
    }

    /**
     * 查找节点
     * @param object
     * @return
     */
    public Node findNode(Object object){
        Node current = head;
        int tempSize = size;
        while(tempSize > 0){
            if(current.data.equals(object)){
                return current;
            }else{
                current = current.next;
                tempSize--;
            }
        }
        return null;
    }

    /**
     * 删除节点
     * @param object
     * @return
     */
    public boolean deleteNode(Object object){
        if(size == 0){
            return false;
        }
//        Node current = head;
//        int tempSize = size;
//        while(tempSize > 0){
//            if(head.data.equals(object)){
//                head = head.next;
//                return true;
//            }else{
//                if(current.next.data.equals(object)){
//                    current.next = current.next.next;
//                    return true;
//                }else{
//                    current = current.next;
//                    tempSize--;
//                }
//            }
//        }

        Node previous = head;
        Node current = head;
        while(!current.data.equals(object)){
            if(current.next == null){
                return false;
            }else{
                previous = current;
                current = current.next;
            }
        }
        if(current == head){
            head = head.next;
            size--;
        }else{
            previous.next = current.next;
            size--;
        }
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
     * 打印链表
     */
    public void display(){
        if(isEmpty()){
            System.out.println("[]");
        }else{
            Node current = head;
            while(current != null){
                System.out.println(current.data.toString());
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addHead("D");
        singleLinkedList.addHead("C");
        singleLinkedList.addHead("B");
        singleLinkedList.addHead("A");
        singleLinkedList.deleteNode("B");
        System.out.println(singleLinkedList.findNode("E"));
        singleLinkedList.display();
    }
}
