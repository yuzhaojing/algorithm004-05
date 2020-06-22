package leetcode;

public class Leetcode_226_InvertBinaryTree {

    public static void main(String[] args) {

    }


    private static TreeNode method1(TreeNode root) {
        if (root == null) return null;

        TreeNode left = method1(root.left);
        TreeNode right = method1(root.right);

        root.left = right;
        root.right = left;

        return root;
    }
}
