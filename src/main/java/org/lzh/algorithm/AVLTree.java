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

    class AVLTreeNode<T extends Comparable<T>>{
        T value;
        int height;
        AVLTreeNode<T> left;
        AVLTreeNode<T> right;

        public AVLTreeNode(T value, int height, AVLTreeNode<T> left, AVLTreeNode<T> right){
            this.value = value;
            this.height = height;
            this.left = left;
            this.right = right;
        }
    }
}
