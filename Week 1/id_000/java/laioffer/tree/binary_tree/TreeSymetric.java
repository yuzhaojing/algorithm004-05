package laioffer.tree.binary_tree;

import laioffer.tree.TreeNode;

public class TreeSymetric {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println(isSymetric(root));
    }

    private static boolean isSymetric(TreeNode root) {
        // base case
        if (root == null) {
            return true;
        }

        return isSymetricHelper(root.left, root.right);
    }

    /**
     * 时间复杂度O(n)
     * 分析思路: 有n个node，最多有n/2个配对，每个配对时间复杂度为O(1)
     *
     * 空间复杂度O(n)
     * 没有说是平衡二叉树，最差情况有n层，最好情况为logn层
     */
    private static boolean isSymetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.value != right.value) {
            return false;
        }

        return isSymetricHelper(left.left, right.right) &&
                isSymetricHelper(left.right, right.left);
    }

    private static boolean isIdetical(TreeNode root) {
        // base case
        if (root == null) {
            return true;
        }

        return isIdeticalHelper(root.left, root.right);
    }

    private static boolean isIdeticalHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.value != right.value) {
            return false;
        }

        return isIdeticalHelper(left.left, right.left) && isIdeticalHelper(left.right, right.right) ||
                isIdeticalHelper(left.left, right.right) && isIdeticalHelper(left.right, right.left);
    }
}
