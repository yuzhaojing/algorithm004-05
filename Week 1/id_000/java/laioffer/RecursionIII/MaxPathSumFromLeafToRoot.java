package laioffer.RecursionIII;

import laioffer.tree.TreeNode;

public class MaxPathSumFromLeafToRoot {

    public static void main(String[] args) {

    }

    /**
     * input: root TreeNode
     * output: int (两个叶子节点之间的最大值)
     * 假设：root != null
     * 如果不符合假设，那么这棵树是空的，返回Integer.MIN_VALUE
     *
     * mid level:
     *  1、每次向下一层递归的时候，存储之前的和
     *  2、每次到达叶子节点的时候，尝试更新最大和
     *
     * time = O(n)
     * space = O(height)
     */
    public int maxPathSumLeafToRoot(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return 0;
        }

        int[] max = new int[] {Integer.MIN_VALUE};
        helper(root, 0, max);
        return max[0];
    }

    private void helper(TreeNode root, int sum, int[] max) {
        if (root == null) {
            return;
        }

        sum += root.key;
        if (root.left == null && root.right == null) {
            max[0] = Math.max(max[0], sum);
            return;
        }

        helper(root.left, sum, max);
        helper(root.right, sum, max);
    }
}
