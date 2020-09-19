package laioffer.RecursionIII;

import laioffer.tree.TreeNode;

public class BalanceBinaryTree {

    /**
     * time = O(n)
     * space = O(height)
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int height = helper(root);
        return height != Integer.MIN_VALUE;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = helper(root.left);
        int right = helper(root.right);

        if ((Math.abs(left - right) > 1)) {
            return Integer.MIN_VALUE;
        }

        return Math.max(left, right) + 1;
    }
}
