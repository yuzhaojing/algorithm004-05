package laioffer.tree;

public class BinaryTreeDiameter {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(5);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(8);
        root.left.left.right.left = new TreeNode(9);
        root.left.left.right.left.left = new TreeNode(10);
        root.right = new TreeNode(6);

        System.out.println(new BinaryTreeDiameter().diameter(root));
    }

    /**
     * time = O(n)
     * <p>
     * space = O(height)
     */
    public int diameter(TreeNode root) {
        // Write your solution here
        int[] diameter = {0};
        if (root == null || root.left == null || root.right == null) {
            return diameter[0];
        }

        diameter(root, diameter);
        return diameter[0];
    }

    private void diameter(TreeNode root, int[] diameter) {
        if (root == null) {
            return;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight + 1);
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
