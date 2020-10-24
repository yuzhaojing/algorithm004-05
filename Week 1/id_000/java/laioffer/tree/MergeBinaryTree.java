package laioffer.tree;

public class MergeBinaryTree {

    public static void main(String[] args) {
        TreeNode left = ArrayToTree.fromArrayToTree(new String[]{"1", "1", "4", "2"});
        TreeNode right = ArrayToTree.fromArrayToTree(new String[]{"2", "2", "2", "#", "4", "#", "3"});

        TreeNode root = mergeBinaryTrees(left, right);
    }

    public static TreeNode mergeBinaryTrees(TreeNode root1, TreeNode root2) {
        // Write your solution here
        if (root1 == null && root2 == null) {
            return null;
        } else if (root1 == null) {
            return root2;
        } else if (root2 == null) {
            return root1;
        }

        root1.key += root2.key;
        root1.left = mergeBinaryTrees(root1.left, root2.left);
        root1.right = mergeBinaryTrees(root1.right, root2.right);
        return root1;
    }
}
