package laioffer.Exam.mid;


import laioffer.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaxSum {

    public static void main(String[] args) {
        System.out.println(new MaxSum().maxSum(new TreeNode(1)));
    }

    /**
     * 假设：root != null
     * 如果root == null，那么该题没有解，与面试官商量返回值
     * 暂定返回值为Integer.MIN_VALUE
     * <p>
     * high level: 使用recursion进行求解
     * mid level: recursion三部曲
     * 1.向左右孩子要什么？
     *   要的是左右子树从叶子节点到左右子节点的最大值之和
     *  （重点：一定要从叶子节点返回）
     * 2.在本层需要做什么？
     *   1、如果左右子树都为null，那么表示本身就是叶子节点
     *   2、如果左右子树有一个为null，那么表示本身只有一个子树，不符合题意的从叶子节点到叶子节点
     *   3、如果左右子树都不为null，说明当前节点是有左右叶子节点的，符合题意
     * 用left + right + root.val和全局的最大值进行比较，如果大于就更新
     * 3.给父节点返回什么？
     *   1、返回root.val
     *   2、left == 0 ? right + root.val : left + root.val;
     *   3、Math.max(left, right) + root.val
     * <p>
     * time = O(n)
     * space = O(height) call stack
     */

    public int maxSum(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int[] globalMax = new int[1];
        maxSum(root, globalMax);
        return globalMax[0];
    }

    private int maxSum(TreeNode root, int[] globalMax) {
        // 当root为null的时候，以当前节点为根的最大和是0
        if (root == null) {
            return 0;
        }

        int left = maxSum(root, globalMax);
        int right = maxSum(root, globalMax);

        // 只有当root的左右子树都不为null的时候，可以更新globalMax
        // 并且返回从该节点到任意叶子节点的最大和
        if (root.left != null && root.right != null) {
            globalMax[0] = Math.max(globalMax[0], left + right + root.key);
            return Math.max(left, right) + root.key;
        }

        // 如果有一个为null的话，返回另一个子树到root的最大和
        return root.left == null ? right + root.key : left + root.key;
    }
}
