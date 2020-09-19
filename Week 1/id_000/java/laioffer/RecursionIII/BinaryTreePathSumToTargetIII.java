package laioffer.RecursionIII;

import laioffer.tree.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class BinaryTreePathSumToTargetIII {

    public static void main(String[] args) {

    }

    /**
     * input: root TreeNode
     *        target int
     * output: boolean
     * 假设：root != null
     * 如果不符合假设，那么这棵树是空的，返回false
     *
     * mid level:
     *  1、每次向下一层递归的时候，存储之前的和到hashset中
     *  2、每次到达新节点的时候，尝试更新最大和
     *
     * time = O(n)
     * space = O(height)
     */
    public boolean exist(TreeNode root, int target) {
        // Write your solution here
        if (root == null) {
            return false;
        }

        Set<Integer> prefixSumSet = new HashSet<>();
        // 默认需要添加0，不然如果第一个元素就是target的话，会漏掉
        prefixSumSet.add(0);
        return helper(root, target, 0, prefixSumSet);
    }

    private boolean helper(TreeNode root, int target, int sum, Set<Integer> prefixSumSet) {
        if (root == null) {
            return false;
        }

        // 使用前缀和性质，当前前缀和减去之前每个位置的前缀和，结果为两个数字之间的和
        // 即target = curSum - X,求得X = curSum - target
        // 所以只要看X在不在prefixSumSet里面就可以知道存不存在target
        sum += root.key;
        if (prefixSumSet.contains(sum - target)) {
            return true;
        }

        // 因为如果有负数的话，不同位置的前缀和可能会相同
        // 所以需要记录当前元素是否重复，没有重复的话，在后面才需要删除
        boolean needRemove = prefixSumSet.add(sum);

        if (helper(root.left, target, sum, prefixSumSet)) {
            return true;
        }

        if (helper(root.right, target, sum, prefixSumSet)) {
            return true;
        }

        if (needRemove) {
            prefixSumSet.remove(sum);
        }

        return false;
    }
}
