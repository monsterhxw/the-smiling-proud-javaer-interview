package com.github.monsterhxw.external.avltree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xuewei Huang
 * @created 2022-05-22
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int height;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(K key, V value) {
        this.root = add(this.root, key, value);
    }

    /**
     * 判断是否为二叉搜索树
     */
    public boolean isBST() {
        List<K> keys = new ArrayList<>();
        inOrder(this.root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, List<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 判断该二叉树是否为一棵平衡二叉树
     */
    public boolean isBalanced() {
        return isBalanced(this.root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        } else {
            return isBalanced(node.left) && isBalanced(node.right);
        }
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            this.size++;
            return new Node(key, value);
        } else {
            if (key.compareTo(node.key) < 0) {
                node.left = add(node.left, key, value);
            } else if (key.compareTo(node.key) > 0) {
                node.right = add(node.right, key, value);
            } else { // key.compareTo(node.key) == 0
                node.value = value;
            }
            // 更新 height
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
            // 计算平衡因子
            int balanceFactor = getBalanceFactor(node);
            if (Math.abs(balanceFactor) > 1) {
                System.out.println("unbalanced: " + balanceFactor);
            }
            return node;
        }
    }

    /**
     * 获取节点 node 的高度
     */
    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    /**
     * 获取节点 node 的平衡因子
     */
    private int getBalanceFactor(Node node) {
        return getHeight(node.left) - getHeight(node.right);
    }
}
