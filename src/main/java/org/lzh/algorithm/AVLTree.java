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
     * 插入节点，递归
     * 1、根节点为空，创建根节点，高度+1
     * 2、插入值大于root节点，插入至root右节点；小于则插入root左节点
     * 3、循环递归2操作，直至根节点为空，则找到待插入的位置，插入节点并重新计算节点高度
     * 4、插入完节点，检验左右子树是否非平衡；是，则进行调整
     * @param root 树的根节点
     * @param value 待插入节点的值
     * @return 根节点
     */
    private AVLTreeNode<T> insert(AVLTreeNode<T> root, T value){
        if(root == null){
            root = new AVLTreeNode<T>(value, null, null);
        }
        else {
            int tmp = value.compareTo(root.getValue());
            //放入右子树
            if(tmp > 0){
                root.right = insert(root.right, value);
                //插入后高度为2
                if(height(root.right) - height(root.left) == 2){
                    if(value.compareTo(root.right.getValue()) < 0){
                        root = rlRotation(root); //属于右左的情况
                    }
                    else {
                        root = rrRotation(root); //属于右右情况
                    }
                }
            }
            else if(tmp < 0){
                root.left = insert(root.left, value);
                //插入后高度为2
                if(height(root.left) - height(root.right) == 2){
                    if(value.compareTo(root.left.getValue()) < 0){
                        root = llRotation(root); //属于右左的情况
                    }
                    else {
                        root = lrRotation(root); //属于右右情况
                    }
                }
            }
            else {
                System.out.println("不允许添加相同节点！");
            }
        }
        //设置高度
        root.height = max(height(root.left), height(root.right)) + 1;
        return root;
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
        public T getValue() {
            return value;
        }

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
