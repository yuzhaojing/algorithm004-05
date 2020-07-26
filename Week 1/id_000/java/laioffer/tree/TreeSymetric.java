package laioffer.tree;

import laioffer.tree.TreeNode;

public class TreeSymetric {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println(new TreeSymetric().isSymmetric(root));
    }

    /**
     * 时间复杂度O(n)
     * 分析思路: 有n个node，最多有n/2个配对，每个配对时间复杂度为O(1)
     *
     * 空间复杂度O(height)
     * 没有说是平衡二叉树，最差情况有n层，最好情况为logn层
     * 最准确的说法是O(height)
     */
    public boolean isSymmetric(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }

        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.key != right.key) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
