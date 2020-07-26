package laioffer.tree;

public class NodeGetHeight {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);

        System.out.println(getHeight(root, root.right.right, new int[1]));
    }

    private static int getHeight(TreeNode root, TreeNode target, int[] res) {
        // base case
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left, target, res);
        int rightHeight = getHeight(root.right, target, res);

        if (root == target) {
            res[0] = Math.max(leftHeight, rightHeight) + 1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
