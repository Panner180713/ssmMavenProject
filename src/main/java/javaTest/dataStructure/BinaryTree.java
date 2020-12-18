package javaTest.dataStructure;

/**
 * 二叉搜索树
 *
 * @Author chenshoukai
 * @Date 2020/09/09 9:07
 */
public class BinaryTree {

    /**
     * 根节点
     */
    private Node root;

    private static class Node {
        private int data;

        private Node leftChild;

        private Node rightChild;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    /**
     * 查找节点
     *
     * @return
     */
    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.data < key) {
                current = current.rightChild;
            } else if (current.data > key) {
                current = current.leftChild;
            } else {
                return current;
            }
        }
        return null;
    }

    /**
     * 插入节点
     *
     * @param key
     * @return
     */
    public boolean insert(int key) {
        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
            return true;
        } else {
            Node current = root;
            while (current != null) {
                Node parentNode = current;
                if (current.data > key) {
                    current = current.leftChild;
                    if(current == null){
                        parentNode.leftChild = newNode;
                        return true;
                    }
                } else {
                    current = current.rightChild;
                    if(current == null){
                        parentNode.rightChild = newNode;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 中序遍历 二叉搜索树最常用
     * @param node
     */
    public void infixOrder(Node node){
        if(node != null){
            infixOrder(node.leftChild);
            System.out.print(node.data + " ");
            infixOrder(node.rightChild);
        }
    }

    /**
     * 前序遍历
     * @param node
     */
    public void preOrder(Node node){
        if(node != null){
            System.out.println(node.data + " ");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }

    /**
     * 后序遍历
     * @param node
     */
    public void postOrder(Node node){
        if(node != null){
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.println(node.data + " ");
        }
    }

    /**
     * 查找最大值
     * @return
     */
    public Node findMax(){
        Node current = root;
        Node maxNode = current;
        while(current != null){
            maxNode = current;
            current = current.rightChild;
        }
        return maxNode;
    }

    /**
     * 查找最小值
     * @return
     */
    public Node findMin(){
        Node current = root;
        Node minNode = current;
        while(current != null){
            minNode = current;
            current = current.leftChild;
        }
        return minNode;
    }

    /**
     * 删除节点
     * @param key
     * @return
     */
    public boolean delete(int key){
        Node current = root;
        Node parent = current;
        boolean isLeftNode = false;
        while(current.data != key){
            parent = current;
            if(current.data > key){
                current = current.leftChild;
                isLeftNode = true;
            }else{
                current = current.rightChild;
                isLeftNode = false;
            }
            if(current == null){
                return false;
            }
        }
        if(current.leftChild == null && current.rightChild == null){
            //删除的是叶节点
            if(current == root){
                root = null;
            }else if(isLeftNode){
                parent.leftChild = null;
            }else{
                parent.rightChild = null;
            }
        }else if(current.leftChild == null && current.rightChild != null){
            //删除的节点有右子节点
            if(current == root){
                root = current.rightChild;
            }else if(isLeftNode){
                parent.leftChild = current.rightChild;
            }else{
                parent.rightChild = current.rightChild;
            }
        }else if(current.leftChild != null && current.rightChild == null){
            //删除的节点有左子节点
            if(current == root){
                root = current.leftChild;
            }else if(isLeftNode){
                parent.leftChild = current.leftChild;
            }else{
                parent.rightChild = current.leftChild;
            }
        }else{
            //删除的节点既有左子节点又有右子节点
            Node successorParent = current;
            Node successor = current;
            Node iterate = current.rightChild;
            while(iterate != null){
                successorParent = successor;
                successor = iterate;
                iterate = iterate.leftChild;
            }
            if(successor != current.rightChild){
                successorParent.leftChild = successor.rightChild;
                successor.rightChild = current.rightChild;
                parent.rightChild = successor;
                successor.leftChild = current.leftChild;
            }else{
                if(isLeftNode){
                    parent.leftChild = successor;
                }else{
                    parent.rightChild = successor;
                }
                successor.leftChild = current.leftChild;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(4);
        binaryTree.insert(2);
        binaryTree.insert(5);
        binaryTree.insert(1);
        binaryTree.insert(3);
        binaryTree.insert(6);
        binaryTree.infixOrder(binaryTree.root);
        binaryTree.delete(2);
        binaryTree.infixOrder(binaryTree.root);
    }
}
