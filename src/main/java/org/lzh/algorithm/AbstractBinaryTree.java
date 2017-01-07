package org.lzh.algorithm;

import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 二叉树抽象类
 *
 * @author lzh
 * @create 2017-01-07 15:32
 */
public abstract class AbstractBinaryTree <T>{
    protected BTNoode<T> root;
    AbstractBinaryTree(){
        this.root = null;
    }
    public abstract void insert(T v);
    public abstract void remove(T v);
    /**
     * 从node节点开始前序遍历
     * @param node 开始遍历的节点
     */
    private void preTraver(BTNoode<T> node){
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

    private void midTraver(BTNoode<T> node){
        if(node != null){
            midTraver(node.left);
            System.out.println("value: " + node.getValue());
            midTraver(node.right);
        }
    }
    public void midTraver(){
        midTraver(root);
    }

    private void postTraver(BTNoode<T> node){
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
     * 对当前树进行广度优先遍历
     */
    public void BFS(){
        BFS(root);
    }

    /**
     * 广度优先遍历，依赖队列实现
     * @param node
     */
    private void BFS(BTNoode<T> node){
        if(node != null){
            BlockingQueue<BTNoode<T>> queue = new LinkedBlockingDeque<>();
            try {
                queue.put(node);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            while (!queue.isEmpty()){
                BTNoode<T> tmp = queue.poll();
                System.out.println("value: " + tmp.getValue());
                try {
                    if(tmp.left != null){
                        queue.put(tmp.left);
                    }
                    if(tmp.right != null){
                        queue.put(tmp.right);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }

    }

    public void DFS(){
        DFS(root);
    }
    /**
     * 深度优先遍历，深度优先遍历为前序遍历的非递归实现
     * @param node
     */
    private void DFS(BTNoode<T> node){
        Stack<BTNoode<T>> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()){
            BTNoode<T> tmp = stack.pop();
            System.out.println("value: " + tmp.value);
            if(tmp.right != null){
                stack.push(tmp.right);
            }
            if(tmp.left != null){
                stack.push(tmp.left);
            }
        }
    }
    class BTNoode<T>{
        T value;
        BTNoode<T> parent;
        BTNoode<T> left;
        BTNoode<T> right;
        BTNoode(T value, BTNoode<T> l, BTNoode<T> r, BTNoode<T> p){
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
}
