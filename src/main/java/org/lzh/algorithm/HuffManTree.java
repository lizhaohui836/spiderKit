package org.lzh.algorithm;

import java.util.Iterator;
import java.util.Set;

/**
 * 哈夫曼树
 *
 * @author lzh
 * @create 2017-01-08 18:43
 */
public class HuffManTree extends AbstractBinaryTree<Integer>{

    private Set<Integer> leaves;
    HuffManTree(Set<Integer> leaves){
        this.leaves = leaves;
        init(leaves);
    }

    private void init(Set<Integer> leaves){
        BTNoode<Integer> pNode = null;
        MinHeap<BTNoode<Integer>> minHeap = new MinHeap<>();
        Iterator<Integer> iterator = leaves.iterator();
        while (iterator.hasNext()){
            BTNoode<Integer> node = new BTNoode<>(iterator.next(), null, null, null);
            minHeap.insert(node);
        }
        for(int i = 0; i < leaves.size() - 1; i++){
            BTNoode<Integer> left = minHeap.poll();
            BTNoode<Integer> right = minHeap.poll();
            int parrent = left.getValue() + right.getValue();
            pNode = new BTNoode<>(parrent, null, null, null);
            pNode.left = left;
            pNode.right = right;
            left.parent = pNode;
            right.parent = pNode;
            minHeap.insert(pNode);
        }
        root = pNode;
        minHeap.destroy();
    }
    @Override
    public void insert(Integer v) {
        Set<Integer> sets = this.leaves;
        sets.add(v);
        init(sets);
    }

    @Override
    public void remove(Integer v) {
        Set<Integer> sets = this.leaves;
        sets.remove(v);
        init(sets);
    }

}
