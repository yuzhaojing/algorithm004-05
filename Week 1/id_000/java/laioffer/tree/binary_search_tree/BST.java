package laioffer.tree.binary_search_tree;

import laioffer.tree.TreeNode;

public class BST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(20);

//        System.out.println(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        printBSTInRange(root, 11, 16);
    }

    /**
     * 时间复杂度O(n^2) 每个节点都需要将子节点全部遍历一次
     * 空间复杂度O(n) 最坏情况n层，每层O(1)的空间复杂度
     */
    private static boolean isBST(TreeNode root, int min, int max) {
        // base case
        if (root == null) return true;

        if (root.value > max || root.value < min) return false;

        return isBST(root.left, min, root.value) && isBST(root.right, root.value, max);
    }

    private static void printBSTInRange(TreeNode root, int min, int max) {
        // base case
        if (root == null) return;

        if (root.value > min) {
            printBSTInRange(root.left, min, max);
        }

        if (root.value >= min && root.value <= max) {
            System.out.println(root.value);
        }

        if (root.value < max) {
            printBSTInRange(root.right, min, max);
        }
    }
}
