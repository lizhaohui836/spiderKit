package org.lzh.trie;

import java.util.Arrays;

/**
 * 字典树类
 *
 * @author BFD_499
 * @create 2016-11-04 16:27
 */
public class TrieTree<T> implements Comparable<TrieTree<T>>{
    private TrieTree[] branches = null;
    private char v;
    //状态 1: 2: 3:到达末端，即代表一个词语
    private int status = 1;
    //其他可拓展参数，用于对字典树上节点增加业务参数
    private T param = null;

    private TrieTree(char v){
        this.v = v;
    }
    public TrieTree(){
        
    }
    public TrieTree(char v, int status, T param){
        this.v = v;
        this.status = status;
        this.param = param;
    }


    /**
     * 插入单词
     * 遍历word，每个char对应于TrieTree中的节点
     * @param word
     */
    public void add(String word, T t) {
        TrieTree<T> tempTrie = this;
        for(int i = 0; i < word.length(); i++){
            //是否是最后一个字符
            if(word.length() == i+1){
                tempTrie.addBranches(new TrieTree<T>(word.charAt(i), 3, t));
            }else{
                tempTrie.addBranches(new TrieTree<T>(word.charAt(i), 1, null));
            }
            tempTrie = tempTrie.branches[tempTrie.getIdex(word.charAt(i))];
        }
    }

    public synchronized void remove(String word) {
        getBranch(word).setStatus(1);
    }

    public void clear() {
        this.branches = null;
    }

    public boolean contains(String word){
        TrieTree<T> tempTrie = this;
        for(int i = 0; i < word.length(); i++){
            int index = tempTrie.getIdex(word.charAt(i));
            if(index < 0){
                return false;
            }
            tempTrie = tempTrie.branches[index];
        }
        if(tempTrie.status == 3){
            return true;
        }
        else{
            return false;
        }
    }
    public synchronized void addBranches(TrieTree<T> branch) {
        //branches是否为空
        if(branches == null){
            branches = new TrieTree[0];
        }
        //获取将要把c插入到branches中的位置，利用二分查找法
        int index = getIdex(branch.getV());
        if(index >= 0){
            //index存在该节点
            if(this.branches[index] == null){
                this.branches[index] = branch;
            }
            switch (branch.getStatus()) {
                case -1:
                    this.branches[index].setStatus(1);
                    break;
                case 1:
                    if (this.branches[index].getStatus() == 3) {
                        this.branches[index].setStatus(2);
                    }
                    break;
                case 3:
                    if (this.branches[index].getStatus() != 3) {
                        this.branches[index].setStatus(3);
                    }
                    this.branches[index].setParam(branch.getParam());

            }
        }else{
            //插入至-(index + 1)的位置
            int insert = - (index + 1);
            TrieTree<T>[] newTrieTree = new TrieTree[branches.length + 1];
            System.arraycopy(branches, 0, newTrieTree, 0, insert);
            System.arraycopy(branches, insert, newTrieTree, insert + 1, branches.length - insert);
            newTrieTree[insert] = branch;
            this.branches = newTrieTree;
        }
    }

    /**
     * 二分查找返回index
     * @param c
     * @return
     */
    private int getIdex(char c) {
        if(branches == null){
            return -1;
        }
        return Arrays.binarySearch(this.branches, new TrieTree<T>(c));
    }

    /**
     * 获取word所在的最后一个节点，即单词末端节点
     * @param word
     * @return
     */
    public TrieTree<T> getBranch(String word){
        TrieTree<T> tempTrieTree = this;
        int index = 0;
        for(int i = 0; i < word.length(); i++){
            index = tempTrieTree.getIdex(word.charAt(i));
            if(index < 0)
                return null;
            if((tempTrieTree = tempTrieTree.branches[index]) == null)
                return null;
        }
        return tempTrieTree;

    }

    public char getV() {
        return v;
    }

    public int compareTo(TrieTree t) {
        if(this.v > t.v)
            return 1;
        if(this.v < t.v)
            return -1;
        return 0;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }

    public T getParam() {
        return param;
    }
    public void setParam(T t){
        this.param = t;
    }
}
