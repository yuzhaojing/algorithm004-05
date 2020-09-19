package laioffer.RecursionIII;

import laioffer.tree.TreeNode;

public class CheckIfBinaryTreeIsBalanced {

    public static void main(String[] args) {

    }

    /**
     * input: root TreeNode
     * output: boolean (是否是balanced)
     * 假设：root != null
     * 如果不符合假设，那么这棵树是空的，返回true
     *
     * mid level:
     *  1、向左右孩子要什么？ 要左右子树的高度
     *  2、在本层做什么？
     *     1. 如果左右子树高度差大于1，说明该节点不平衡，返回-1
     *     2. 如果左右子树有一至少一边返回-1，说明该节点不平衡，返回-1
     *
     * time = O(n)
     * space = O(height)
     */
    public boolean isBalanced(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }

        return helper(root) != -1;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = helper(root.left);
        int right = helper(root.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }
}
