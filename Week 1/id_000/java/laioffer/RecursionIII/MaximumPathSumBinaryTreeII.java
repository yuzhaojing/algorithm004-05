package laioffer.RecursionIII;

import laioffer.tree.TreeNode;

public class MaximumPathSumBinaryTreeII {

    public static void main(String[] args) {

    }

    /**
     * input: root TreeNode
     * output: int (任意两个节点之间的最大值)
     * 假设：root != null
     * 如果不符合假设，那么这棵树是空的，返回Integer.MIN_VALUE
     *
     * mid level:
     *  1、向左右孩子要什么？ 要左右子树的最大和
     *  2、在本层做什么？
     *     1.如果左右子树的最大和小于0，则将值置为0，因为负数会减小本层的最大和
     *     2.用left + right + root.key尝试更新全局最大和
     *     3.返回Math.max(left, right) + root.key (返回以当前node作为起点的最大和)
     *
     * time = O(n)
     * space = O(height)
     */
    public int maxPathSum(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return 0;
        }

        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);

        int[] max = new int[] {Integer.MIN_VALUE};
        helper(root, max);
        return max[0];
    }

    private int helper(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }

        int left = helper(root.left, max);
        int right = helper(root.right, max);

        left = left < 0 ? 0 : left;
        right = right < 0 ? 0 : right;
        max[0] = Math.max(max[0], left + right + root.key);
        return Math.max(left, right) + root.key;
    }
}
