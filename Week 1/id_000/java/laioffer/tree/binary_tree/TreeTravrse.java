package laioffer.tree.binary_tree;

import laioffer.tree.TreeNode;

public class TreeTravrse {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        preOrder(root);
        System.out.println("--------------");
        inOrder(root);
        System.out.println("--------------");
        postOrder(root);
    }

    private static void preOrder(TreeNode root) {
        // base case
        if (root == null) return;

        System.out.println(root.value);
        preOrder(root.left);
        preOrder(root.right);
    }

    private static void inOrder(TreeNode root) {
        // base case
        if (root != null) return;

        inOrder(root.left);
        System.out.println(root.value);
        inOrder(root.right);
    }

    private static void postOrder(TreeNode root) {
        // base case
        if (root != null) return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.value);
    }
}
