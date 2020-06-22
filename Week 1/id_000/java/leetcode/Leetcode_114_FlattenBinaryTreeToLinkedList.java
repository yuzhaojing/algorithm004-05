package leetcode;

public class Leetcode_114_FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        method1(root);
        System.out.println(root);
    }

    private static void method1(TreeNode root) {
        if (root == null) return;

        method1(root.left);
        method1(root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;

        while (root.right != null) {
            root = root.right;
        }

        root.right = tmp;
    }
}



























