package org.lzh.algorithm;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 哈弗曼树测试
 *
 * @author lzh
 * @create 2017-01-08 20:05
 */
public class HuffManTreeTest {
    @Test
    public void huffManTree(){
        int[] a = {5,8,4,1,9,3,2,7,12};
        Set<Integer> set = new HashSet<>();
        for(int i : a){
            set.add(i);
        }
        AbstractBinaryTree<Integer> huffManTree = new HuffManTree(set);
        System.out.println("---------广度优先遍历----------");
        huffManTree.BFS();
        huffManTree.insert(10);
        System.out.println("---------广度优先遍历----------");
        huffManTree.BFS();
    }
}
