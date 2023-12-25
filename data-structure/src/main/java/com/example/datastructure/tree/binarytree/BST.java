package com.example.datastructure.tree.binarytree;

// 깊이 우선 탐색
public class BST {

    public static void main (String[] args) {
        // value 8을 찾아보자

        Node head = new Node(15);
        Node son1 = new Node(7);
        son1.left = new Node(8);
        head.left = son1;
        head.right = new Node(40);

        Node dfs = search(head, 8);
        System.out.println(dfs.getValue());
    }

    public static Node search(Node node, int target) {
        if (node.equals(target)) return node;
        if (node == null) return null;

        if (node.value < target && node.left != null) {
            return search(node.left, target);
        }
        if (node.value > target && node.right != null) {
            return search(node.right, target);
        }
    }
}
