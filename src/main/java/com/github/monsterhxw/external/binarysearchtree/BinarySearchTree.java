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
        root = add(root, data);
    }

    private Node add(Node node, E data) {
        if (node == null) {
            size++;
            return new Node(data);
        }
        if (data.compareTo(node.data) < 0) {
            node.left = add(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = add(node.right, data);
        }
        return node;
    }

    public boolean contains(E data) {
        return contains(root, data);
    }

    private boolean contains(Node node, E data) {
        if (node == null) {
            return false;
        }
        if (data.compareTo(node.data) < 0) {
            return contains(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            return contains(node.right, data);
        } else {
            return true;
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBSTStr(root, 0, sb);
        return sb.toString();
    }

    private void generateBSTStr(Node node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthStr(depth) + "null\n");
            return;
        }
        sb.append(generateDepthStr(depth) + node.data + "\n");
        generateBSTStr(node.left, depth + 1, sb);
        generateBSTStr(node.right, depth + 1, sb);
    }

    private String generateDepthStr(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    /**
     * 迭代实现
     */
    public void add2(E data) {
        if (root == null) {
            size++;
            root = new Node(data);
            return;
        }
        Node p = root;
        while (p != null) {
            if (data.compareTo(p.data) < 0) {
                if (p.left == null) {
                    p.left = new Node(data);
                    size++;
                    return;
                } else {
                    p = p.left;
                }
            } else if (data.compareTo(p.data) > 0) {
                if (p.right == null) {
                    p.right = new Node(data);
                } else {
                    p = p.right;
                }
            } else {
                return;
            }
        }
    }
}
