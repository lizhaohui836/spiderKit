package org.lzh.algorithm;

/**
 * 二叉查找树
 * @author lzh
 * @create 2016-12-27 21:29
 */
public class BinarySearchTree<T extends Comparable<T>>{
    private BSTNoode<T> root;
    public BinarySearchTree(){
        this.root = null;
    }

    /**
     * 将节点Node插入至tree中
     * @param tree 插入的树
     * @param node 待插入的节点
     */
    private void insert(BinarySearchTree<T> tree, BSTNoode<T> node){
        if(tree.root == null){
            tree.root = node;
            return;
        }
        BSTNoode<T> tmp = null;
        BSTNoode<T> x =tree.root;
        while(x != null){
            tmp = x;
            if(node.getValue().compareTo(x.getValue()) > 0){
                x = x.right;
            }
            else {
                x = x.left;
            }
        }
        node.parent = tmp;
        if(node.getValue().compareTo(tmp.getValue()) > 0){
            tmp.right = node;
        }
        else {
            tmp.left = node;
        }
    }
    /**
     * 将value值的Node节点插入至树中
     * @param value 待插入节点的值
     */
    public void insert(T value){
        BinarySearchTree<T> tree = this;
        BSTNoode<T> node = new BSTNoode<T>(value, null, null, null);
        insert(this, node);
    }

    private void preTraver(BSTNoode<T> node){
        if(node != null){
            System.out.println("value: " + node.getValue());
            preTraver(node.left);
            preTraver(node.right);
        }
    }
    public void preTraver(){
        preTraver(root);
    }

    private void midTraver(BSTNoode<T> node){
        if(node != null){
            midTraver(node.left);
            System.out.println("value: " + node.getValue());
            midTraver(node.right);
        }
    }
    public void midTraver(){
        midTraver(root);
    }

    private void postTraver(BSTNoode<T> node){
        if(node != null){
            postTraver(node.left);
            postTraver(node.right);
            System.out.println("value: " + node.getValue());
        }
    }
    public void postTraver(){
        postTraver(root);
    }
    class BSTNoode<T extends Comparable<T>>{
        T value;
        BSTNoode<T> parent;
        BSTNoode<T> left;
        BSTNoode<T> right;
        BSTNoode(T value, BSTNoode<T> l, BSTNoode<T> r, BSTNoode<T> p){
            this.value = value;
            this.parent = p;
            this.left = l;
            this.right = r;
        }
        public T getValue() {
            return value;
        }
        public void setValue(T value){
            this.value = value;
        }
        public String toString(){
            return "value:" + value;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
        binarySearchTree.insert(5);
        binarySearchTree.insert(1);
        binarySearchTree.insert(7);
        binarySearchTree.insert(9);
        binarySearchTree.insert(2);
        binarySearchTree.insert(3);
        binarySearchTree.insert(10);
        binarySearchTree.insert(8);
        binarySearchTree.insert(4);
        binarySearchTree.insert(6);
        System.out.println("----------preTraver---------");
        binarySearchTree.preTraver();
        System.out.println("----------------------------");
        System.out.println("----------midTraver---------");
        binarySearchTree.midTraver();
        System.out.println("----------------------------");
        System.out.println("----------postTraver---------");
        binarySearchTree.postTraver();
        System.out.println("----------------------------");
    }
}
