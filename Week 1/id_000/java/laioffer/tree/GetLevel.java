package laioffer.tree;

public class GetLevel {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);

        System.out.println(getLevel(root, root.right.right, 1));
    }

    private static int getLevel(TreeNode root, TreeNode target, int level) {
        // base case
        if (root == null) {
            return 0;
        }

        if (root == target) {
            return level;
        }

        int leftLevel = getLevel(root.left, target, level + 1);
        int rightLevel = getLevel(root.right, target, level + 1);

        return leftLevel == 0 ? rightLevel : leftLevel;
    }
}
