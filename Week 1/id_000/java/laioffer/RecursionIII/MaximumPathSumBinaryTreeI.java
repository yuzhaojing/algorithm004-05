package laioffer.RecursionIII;

import laioffer.tree.TreeNode;

public class MaximumPathSumBinaryTreeI {

    public static void main(String[] args) {

    }

    /**
     * 期中考试第二题！
     *
     * input: root TreeNode
     * output: int (两个叶子节点之间的最大值)
     * 假设：root != null
     * 如果不符合假设，那么这棵树是空的，返回Integer.MIN_VALUE
     *
     * mid level:
     *  1、向左右孩子要什么？ 要左右子树的从叶子节点的最大和
     *  2、在本层做什么？
     *     1.如果左右子树都没有，那么该节点是叶子节点，返回该节点的值
     *     2.如果左右子树只有一个，返回存在的那个子树返回的最大值加上自己的值
     *     3.如果左右子树都存在，获取左右子树返回的最大和，加上自己的值，和全局最大值比较
     *       返回左右子树返回的最大和中较大的，加上自己的值返回给上一层
     *
     * time = O(n)
     * space = O(height)
     */
    public int maxPathSum(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        int[] max = new int[1];
        // 初始值必须为最小值，如果没有更改满足条件的node，返回的值是符合题意的
        max[0] = Integer.MIN_VALUE;
        helper(root, max);
        return max[0];
    }

    private int helper(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }

        int left = helper(root.left, max);
        int right = helper(root.right, max);

        if (root.left != null && root.right != null) {
            max[0] = Math.max(max[0], left + right + root.key);
            return Math.max(left, right) + root.key;
        }

        return root.left == null ? right + root.key : left + root.key;
    }
}
