package javaTest.dataStructure;

import java.util.Stack;

/**
 * 双向链表
 * @Author chenshoukai
 * @Date 2020/09/05 15:11
 */
public class TwoWayLinkedList {

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
        /**
         * 前驱
         */
        Node prev;

        /**
         * 后继
         */
        Node next;

        Object data;

        public Node(Object data) {
            this.data = data;
        }
    }

    /**
     * 添加头节点
     * @param object
     */
    public void addHead(Object object){
        Node newHead = new Node(object);
        if(size == 0){
            head = newHead;
            tail = newHead;
        }else{
            newHead.next = head;
            head.prev = newHead;
            head = newHead;
        }
        size++;
    }

    /**
     * 添加尾节点
     * @param object
     */
    public void addTail(Object object){
        Node newTail = new Node(object);
        if(size == 0){
            head = newTail;
            tail = newTail;
        }else{
            tail.next = newTail;
            newTail.prev = tail;
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
            head.prev = null;
        }
        size--;
        return true;
    }

    /**
     * 删除尾节点
     * @return
     */
    public boolean deleteTail(){
        if(size == 0){
            return false;
        }else if(size == 1){
            head = null;
            tail = null;
        }else{
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return true;
    }

    /**
     * 遍历链表
     */
    public void display(){
        Node current = head;
        while(current != null){
            System.out.println(current.data.toString());
            current = current.next;
        }
    }

    public static void main(String[] args) {
        TwoWayLinkedList twoWayLinkedList = new TwoWayLinkedList();
        twoWayLinkedList.addHead("D");
        twoWayLinkedList.addHead("C");
        twoWayLinkedList.addHead("B");
        twoWayLinkedList.addHead("A");
        twoWayLinkedList.deleteTail();
        twoWayLinkedList.addTail("E");
        twoWayLinkedList.display();
    }
}
