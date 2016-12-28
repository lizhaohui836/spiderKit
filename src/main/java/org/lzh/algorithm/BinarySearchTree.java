package org.lzh.algorithm;

/**
 * 二叉查找树
 * 节点前驱：比该节点稍小的节点，即所有比该节点小的节点中最大的节点
 * 节点后继：比该节点稍大的节点，即所有比该节点大的节点中最小的节点
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

    /**
     * 删除某节点，分三种情况，后两种情况需考虑删除节点是否为root节点
     * 1、如果该节点没有孩子，则直接删除
     * 2、如果该节点有一个孩子，则删除节点，并将该节点的子树与父节点直接关联即可
     * 3、有两个孩子，不能直接删除该节点，应该找到该节点的后继节点，将后继节点覆盖该节点，并删除后继节点
     * @param tree
     * @param node
     * @return
     */
    private BSTNoode<T> remove(BinarySearchTree<T> tree, BSTNoode<T> node){
        if(node == null){
            return null;
        }
        BSTNoode<T> x = node.parent;
        BSTNoode<T> y = node;
        //无孩子
        if(node.left == null && node.right == null){
            node = null;
            return y;
        }
        //一个孩子
        else if(!(node.left != null && node.right != null)){
            if(node.left != null){
                node.left.parent = x;
                if(node == node.parent.left){
                    x.left = node.left;
                }
                else {
                    x.right = node.left;
                }
            }
            else {
                node.right.parent = x;
                if(node == node.parent.left){
                    x.left = node.right;
                }
                else {
                    x.right = node.right;
                }
            }
            return y;
        }
        //两个孩子
        else {
            y = min(node);
            y.parent = node.parent;
            if(node == node.parent.left){
                x.left = y;
            }
            else {
                x.right = y;
            }
            return y;
        }
    }
    /**
     * 从node节点开始前序遍历
     * @param node 开始遍历的节点
     */
    private void preTraver(BSTNoode<T> node){
        if(node != null){
            System.out.println("value: " + node.getValue());
            preTraver(node.left);
            preTraver(node.right);
        }
    }

    /**
     * 对二叉查找树进行前序遍历
     */
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

    /**
     * 查找节点的前驱节点
     * @param node 当前节点
     * @return preNode 前驱节点
     */
    public BSTNoode<T> precursor(BSTNoode<T> node){
       return null;
    }

    /**
     * 返回节点的后继节点
     * <ul>
     *     <li>1、该节点存在右子树，则后继节点为右子树中最小的节点</li>
     *     <li>2、该节点不存在右子树，则表明该节点是以该节点为root节点的子树中最大值，后继节点应在父节点中。
     *     并且满足规则：</li>
     *     <li>  1> 向上找出的子树为父节点的左孩子</li>
     *     <li>  2> 满足1的第一个父节点</li>
     * </ul>
     * @param node 当前节点
     * @return sucNode 当前节点的后继节点
     */
    public BSTNoode<T> successor(BSTNoode<T> node){
        if(node.parent == null){
            return null;
        }
        //有右子树
        if(node.right != null){
            //找出最小值的节点
            return min(node.right);
        }
        //没有右子树
        BSTNoode<T> y = node.parent;
        BSTNoode<T> x = node;
        while (y != null){
            if(y.left == x){
                return y;
            }
            y = y.parent;
            x = x.parent;
        }
        return null;
    }

    /**
     * 找出以node为root的子树的最小节点
     * @param node
     * @return 最小节点
     */
    private BSTNoode<T> min(BSTNoode<T> node) {
        if(node.left == null){
            return node;
        }
        BSTNoode<T> x = node.left;
        while (x.left != null){
            x = x.left;
        }
        return x;
    }

    /**
     * 找出以node为root的子树的最大节点
     * @param node
     * @return
     */
    private BSTNoode<T> max(BSTNoode<T> node){
        if(node.right == null){
            return node;
        }
        BSTNoode<T> x = node.right;
        while (x.right != null){
            x = x.right;
        }
        return x;
    }

    static class BSTNoode<T extends Comparable<T>>{
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
        BSTNoode<Integer> node = new BSTNoode<Integer>(7,null,null,null);
        binarySearchTree.insert(5);
        binarySearchTree.insert(1);
//        binarySearchTree.insert(7);
        binarySearchTree.insert(binarySearchTree, node);
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
        System.out.println("7的后继节点为" + binarySearchTree.successor(node).getValue());
    }
}
