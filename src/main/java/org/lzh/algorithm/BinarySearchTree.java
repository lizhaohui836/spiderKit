package org.lzh.algorithm;


/**
 * 二叉查找树
 * 节点前驱：比该节点稍小的节点，即所有比该节点小的节点中最大的节点
 * 节点后继：比该节点稍大的节点，即所有比该节点大的节点中最小的节点
 * @author lzh
 * @create 2016-12-27 21:29
 */
public class BinarySearchTree<T extends Comparable<T>> extends AbstractBinaryTree<T>{
    public BinarySearchTree(){
        super();
    }

    /**
     * 将节点Node插入至tree中
     * @param tree 插入的树
     * @param node 待插入的节点
     */
    private void insert(BinarySearchTree<T> tree, BTNoode<T> node){
        if(root == null){
            tree.root = node;
            return;
        }
        BTNoode<T> tmp = null;
        BTNoode<T> x =tree.root;
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
        BTNoode<T> node = new BTNoode<T>(value, null, null, null);
        insert(this, node);
    }

    /**
     * 删除值为value的节点
     * @param value 值为value的节点
     */
    public void remove(T value){
        BTNoode<T> node = search(value);
        if(node != null){
            remove(this, node);
        }
    }
    /**
     * 删除某节点，分三种情况，后两种情况需考虑删除节点是否为root节点
     * 1、如果该节点没有孩子，则直接删除
     * 2、如果该节点有一个孩子，则删除节点，并将该节点的子树与父节点直接关联即可
     * 3、有两个孩子，不能直接删除该节点，应该找到该节点的后继节点，将后继节点覆盖该节点，并删除后继节点(此时后继节一定是右子树的左孩子
     * 且该后继节点没有左孩子)
     * @param tree
     * @param node
     * @return
     */
    private BTNoode<T> remove(BinarySearchTree<T> tree, BTNoode<T> node){
        if(node == null){
            return null;
        }
        BTNoode<T> y;  //删除的节点
        BTNoode<T> x;  //代替node位置的节点
        //确定要删除的节点y
        if(node.left == null || node.right == null){
            y = node;
        } else {
            //当有两个孩子时，删除的节点为node的后继节点
            y = successor(node);
        }
        if(y.left != null){
            x = y.left;
        } else {
            x = y.right;
        }
        if(x != null){
            x.parent = y.parent;
        }
        if(y.parent == null){
            tree.root = x;
        }
        else if(y == y.parent.left){
            y.parent.left = x;
        }
        else {
            y.parent.right = x;
        }
        if(y != node){
            node.value = y.value;
        }
        return y;
    }

    /**
     * 查找节点的前驱节点
     * @param node 当前节点
     * @return preNode 前驱节点
     */
    public BTNoode<T> precursor(BTNoode<T> node){
        //有左子树
        if(node.left != null){
            //找出最小值的节点
            return max(node.left);
        }
        //没有左子树
        BTNoode<T> y = node.parent;
        BTNoode<T> x = node;
        while (y != null){
            if(y.right == x){
                return y;
            }
            y = y.parent;
            x = x.parent;
        }
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
    public BTNoode<T> successor(BTNoode<T> node){
        //有右子树
        if(node.right != null){
            //找出最小值的节点
            return min(node.right);
        }
        //没有右子树
        BTNoode<T> y = node.parent;
        BTNoode<T> x = node;
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
    private BTNoode<T> min(BTNoode<T> node) {
        if(node.left == null){
            return node;
        }
        BTNoode<T> x = node.left;
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
    private BTNoode<T> max(BTNoode<T> node){
        if(node.right == null){
            return node;
        }
        BTNoode<T> x = node.right;
        while (x.right != null){
            x = x.right;
        }
        return x;
    }

    /**
     * 根据value查找对应节点
     * @param value 待查询的值
     * @return
     */
    private BTNoode<T> search(T value){
        BTNoode<T> x = this.root;
        while(x != null){
            if(value.compareTo(x.value) > 0){
                x = x.right;
            }
            else if(value.compareTo(x.value) < 0){
                x = x.left;
            }
            else{
                break;
            }
        }
        return x;
    }

    public static void main(String[] args) {
        int[] arrays = {5, 1, 7, 8,2,3,4,11,15,12,6};
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        for(int i : arrays){
            binarySearchTree.insert(i);
        }
        System.out.println("----------preTraver---------");
        binarySearchTree.preTraver();
        System.out.println("----------------------------");
        System.out.println("----------midTraver---------");
        binarySearchTree.midTraver();
        System.out.println("----------------------------");
        System.out.println("----------postTraver---------");
        binarySearchTree.postTraver();
        System.out.println("----------------------------");
        System.out.println("----------广度优先遍历--------");
        binarySearchTree.BFS();
        System.out.println("----------深度优先遍历--------");
        binarySearchTree.DFS();

    }
}
