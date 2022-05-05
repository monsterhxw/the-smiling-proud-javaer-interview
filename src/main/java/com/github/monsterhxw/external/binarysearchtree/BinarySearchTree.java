package com.github.monsterhxw.external.binarysearchtree;

/**
 * @author Xuewei Huang
 * @created 2022-05-05
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private class Node {
        private E data;
        private Node left;
        private Node right;

        private Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;

    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向二分搜索树添加新的元素 data
     */
    public void add(E data) {
        if (root == null) {
            root = new Node(data);
        } else {
            add(root, data);
        }
    }

    private void add(Node node, E data) {
        if (data.equals(node.data)) {
            return;
        } else if (data.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new Node(data);
                size++;
                return;
            } else {
                add(node.left, data);
            }
        } else if (data.compareTo(node.data) > 0) {
            if (node.right == null) {
                node.right = new Node(data);
                size++;
                return;
            } else {
                add(node.right, data);
            }
        }
    }
}
