package laioffer.RecursionIII;

import laioffer.tree.TreeNode;

public class MaximumPathSumBinaryTreeIII {

    public static void main(String[] args) {

    }

    /**
     * input: root TreeNode
     *        target int
     * output: int
     * 假设：root != null
     * 如果不符合假设，那么这棵树是空的，返回Integer.MIN_VALUE;
     *
     * high level: 使用recursion，直上直下的方法解答
     * mid level:
     *  1、在遍历的过程中，对任意一条path，将路过的节点看成数组中的元素，对其进行最大和子数组的计算，并更新全局变量
     *
     * time = O(n)
     * space = O(height)
     */
    public int maxPathSum(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        int[] max = new int[] {Integer.MIN_VALUE};
        helper(root, 0, max);
        return max[0];
    }

    private void helper(TreeNode root, int prev, int[] max) {
        if (root == null) {
            return;
        }

        if (prev < 0) {
            prev = root.key;
        } else {
            prev += root.key;
        }

        max[0] = Math.max(max[0], prev);
        helper(root.left, prev, max);
        helper(root.right, prev, max);
    }

    /**
     * input: root TreeNode
     *        target int
     * output: int
     * 假设：root != null
     * 如果不符合假设，那么这棵树是空的，返回Integer.MIN_VALUE;
     *
     * high level: 使用recursion三部曲的方法解答
     * mid level:
     *  1、向左右孩子要什么？ 要以左右孩子为起点的最大和
     *  2、在本层做什么？    根据左右孩子返回的最大和，计算本层的最大和
     *  3、向父节点返回什么？ 返回计算出得最大和
     *
     * time = O(n)
     * space = O(height)
     */
    public int maxPathSum1(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return Integer.MIN_VALUE;
        }

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

        int maxSum = Math.max(0, Math.max(left, right)) + root.key;
        max[0] = Math.max(max[0], maxSum);

        return maxSum;
    }
}
