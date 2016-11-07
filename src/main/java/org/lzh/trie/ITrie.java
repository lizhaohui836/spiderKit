package org.lzh.trie;

/**
 * Created by BFD_499 on 2016/11/4.
 */
public interface ITrie<T> {
    public void add(String word, T t);  //字典树中增加单词
    public void remove(String word);  //删除单词
    public void clear(); //清空字典树
    public void addBranches(ITrie<T> trie);  //增加子树
}
