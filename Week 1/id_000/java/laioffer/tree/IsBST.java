package laioffer.tree;

public class IsBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(20);

        System.out.println(new IsBST().isBST(root));
    }

    /**
     * 时间复杂度O(n)
     * worst case: 确实是一个BST，每个节点遍历一次
     *
     * 空间复杂度O(height)
     */
    public boolean isBST(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }

        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.key <= min || root.key >= max) {
            return false;
        }

        return isBST(root.left, min, root.key) && isBST(root.right, root.key, max);
    }
}
