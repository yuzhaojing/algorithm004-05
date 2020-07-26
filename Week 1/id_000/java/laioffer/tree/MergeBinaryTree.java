package laioffer.tree;

public class MergeBinaryTree {

    public static void main(String[] args) {
        TreeNode left = ArrayToTree.fromArrayToTree(new String[]{"1", "1", "4", "2"});
        TreeNode right = ArrayToTree.fromArrayToTree(new String[]{"2", "2", "2", "#", "4", "#", "3"});

        TreeNode root = mergeBinaryTree(left, right);
    }

    private static TreeNode mergeBinaryTree(TreeNode left, TreeNode right) {
        // base case
        TreeNode cur = new TreeNode(0);
        if (left == null && right == null) {
            return null;
        } else if (left == null) {
            cur.key = right.key;
            cur.left = mergeBinaryTree(null, right.left);
            cur.right = mergeBinaryTree(null, right.right);
        } else if (right == null) {
            cur.key = left.key;
            cur.left = mergeBinaryTree(left.left, null);
            cur.right = mergeBinaryTree(left.right, null);
        } else {
            cur.key = left.key + right.key;
            cur.left = mergeBinaryTree(left.left, right.left);
            cur.right = mergeBinaryTree(left.right, right.right);
        }

        return cur;
    }
}
