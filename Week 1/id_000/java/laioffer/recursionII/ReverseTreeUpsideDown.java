package laioffer.recursionII;

import laioffer.tree.TreeNode;

public class ReverseTreeUpsideDown {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);

        TreeNode treeNode = new ReverseTreeUpsideDown().reverseTreeUpsideDown(root);
    }

    /**
     * 将tree的左子树作为子问题，翻转整棵树
     */

    private TreeNode reverseTreeUpsideDown(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }

        TreeNode newRoot = reverseTreeUpsideDown(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}
