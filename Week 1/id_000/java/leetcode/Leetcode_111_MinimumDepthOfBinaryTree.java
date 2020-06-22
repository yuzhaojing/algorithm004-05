package leetcode;

public class Leetcode_111_MinimumDepthOfBinaryTree {

    public static void main(String[] args) {

    }


    private static int method1(TreeNode root) {
        if (root == null) return 0;

        int left = method1(root.left);
        int right = method1(root.right);

        if (root.left == null || root.right == null) return left + right + 1;

        return Math.min(left, right) + 1;
    }
}
