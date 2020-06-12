public class Leetcode_236_LowestCommonAncestor {

    public static void main(String[] args) {

    }


    private static TreeNode method1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = method1(root.left, p, q);
        TreeNode right = method1(root.right, p, q);

        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
}
