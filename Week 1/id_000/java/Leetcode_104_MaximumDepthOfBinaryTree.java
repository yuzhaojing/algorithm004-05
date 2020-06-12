public class Leetcode_104_MaximumDepthOfBinaryTree {

    public static void main(String[] args) {

    }


    private static int method1(TreeNode root) {
        if (root == null) return 0;

        int left = method1(root.left);
        int right = method1(root.right);

        return Math.max(left, right) + 1;
    }
}
