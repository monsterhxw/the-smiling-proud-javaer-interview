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
            // 平衡维护
            // left left
            if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
                return rightRotate(node);
            }
            // right right
            if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
                return leftRotate(node);
            }
            // left right
            if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            // right left
            if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            return node;
        }
    }

    // 对节点 y 进行向右旋转操作，返回旋转后新的根节点 x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转
        x.right = y;
        y.left = T3;

        // 更新 height
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转
        x.left = y;
        y.right = T2;

        // 更新 height
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
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
