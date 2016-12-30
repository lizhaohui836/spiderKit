package org.lzh.algorithm;

/**
 * AVL树，自平衡二叉查找树。在AVL树中任何节点的两个子树的高度最大差别为1，
 * 查找、删除、插入平均和最坏情况下都是O(logn)
 * 节点的平衡因子是它的左子树和右子树高度差，带有平衡因子1、0、-1的节点被
 * 认为是平衡的。带有平衡因子2、-2的节点认为是不平衡的，需要重新平衡这个树。
 *
 * 在AVL树进行插入或者删除操作时，AVL树可能会失去平衡。失去平衡的状态分为4种
 * LeftLeft RightRight LeftRight RightLeft
 * 这四种状态分别可以采用旋转进行调整
 * @author lzh
 * @create 2016-12-29 14:30
 */
public class AVLTree<T extends Comparable<T>> {

    AVLTreeNode<T> root;

    public AVLTree(){this.root = null;}
    /**
     * 插入节点
     * @param tree
     * @param node
     */
    private void insert(AVLTree<T> tree, AVLTreeNode<T> node){
        if(tree.root == null){
            tree.root = node;
            return;
        }
        AVLTreeNode<T> tmp;
        AVLTreeNode<T> x = tree.root;
    }
    /**
     * LeftLeft情况的旋转，返回旋转后的根节点
     * @param node 旋转前根节点（相对）
     * @return node2 旋转后根节点
     */
    private AVLTreeNode<T> llRotation(AVLTreeNode<T> node){
        AVLTreeNode<T> node2 = node.left;
        node.left = node2.right;
        node2.right = node;

        node.height = max(height(node.left), height(node.right)) + 1;
        node2.height = max(height(node2.left), height(node)) + 1;
        return node2;
    }

    /**
     * RightRight情况的旋转，返回旋转后的根节点
     * @param node 旋转前根节点（相对）
     * @return node2 旋转后根节点
     */
    private AVLTreeNode<T> rrRotation(AVLTreeNode<T> node){
        AVLTreeNode<T> node2 = node.right;
        node.right = node2.left;
        node2.left = node;

        node.height = max(height(node.left), height(node.right)) + 1;
        node2.height = max(height(node2.right), height(node)) + 1;
        return node2;
    }

    /**
     * LeftRight情况的旋转
     * @param node 旋转前根节点（相对）
     * @return node2 旋转后根节点
     */
    private AVLTreeNode<T> lrRotation(AVLTreeNode<T> node){
        node.left = rrRotation(node.left);
        return llRotation(node);
    }

    /**
     * RightLeft情况的旋转
     * @param node 旋转前根节点（相对）
     * @return node2 旋转后根节点
     */
    private AVLTreeNode<T> rlRotation(AVLTreeNode<T> node){
        node.right = llRotation(node.right);
        return rrRotation(node);
    }

    /**
     * 比较a b的大小
     * @param a
     * @param b
     * @return 较大的数
     */
    private int max(int a, int b){
        return a > b ? a : b;
    }
    private int height(AVLTreeNode<T> node){
        if(node != null){
            return node.height;
        }
        return 0;
    }
    class AVLTreeNode<T extends Comparable<T>>{
        T value;
        int height;
        AVLTreeNode<T> left;
        AVLTreeNode<T> right;

        public AVLTreeNode(T value, AVLTreeNode<T> left, AVLTreeNode<T> right){
            this.value = value;
            this.height = 0;
            this.left = left;
            this.right = right;
        }
    }
}
