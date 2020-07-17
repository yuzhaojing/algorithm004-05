package laioffer.tree.binary_tree;

import laioffer.tree.TreeNode;

public class TreeHeight {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);

        System.out.println(isBalance(root));
    }

    /**
     * 时间复杂度O(n) 每个节点遍历一次，每次判断为O(1),所以时间复杂度为O(1) * n = O(n)
     * 空间复杂度O(n) 最坏情况n层，每层O(1)的空间复杂度
     */
    private static int getHeight(TreeNode root) {
        // base case
        if (root == null) return 0;

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        return Math.max(left, right) + 1;
    }

    /**
     * 时间复杂度O(nlogn)
     * 分析过程:
     * 每一个节点都需要通过两次getHeight进行判断，每次getHeight的时间复杂度为O(n)
     * 由根节点开始计算，根节点由n个子节点，则平均左右都有n/2个子节点
     * 所有根节点的时间复杂度为O(n/2) + O(n/2) = O(n)
     * 下一层左节点的时间复杂度为O(n/4) + O(n/4) = O(n/2)
     * 下一层右节点的时间复杂度为O(n/4) + O(n/4) = O(n/2)
     * 所以每一层的时间复杂度都为O(n),总时间复杂度为O(n) * 总层数
     * 最坏情况为logn层, 时间复杂度 = O(n) * O(logn) = O(nlogn)
     *
     * 空间复杂度O(logn)
     */
    private static boolean isBalance(TreeNode root) {
        // base case
        if (root == null) return true;

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        if (Math.abs(left - right) > 1) return false;

        return isBalance(root.left) && isBalance(root.right);
    }
}
