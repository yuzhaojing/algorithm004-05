package leetcode;

/**
 * 1、两数之和
 */
public class Leetcode_543_DiameterOfBinaryTree {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);

        System.out.println(method1(node));
    }

    private static int res;

    private static int method1(TreeNode root) {
        if (root == null) return 0;

        depth(root);
        return res;
    }

    private static int depth(TreeNode root) {
        if (root == null) return 0;

        int left = depth(root.left);
        int right = depth(root.right);

        res = Math.max(res, left + right);
        return Math.max(left, right) + 1;
    }
}
