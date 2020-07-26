package laioffer.tree;

public class TweakedIdenticalTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println(new TweakedIdenticalTree().isTweakedIdentical(root, root));
    }

    /**
     * 时间复杂度O(n)
     * 将one和two这两个传入的树作为一个整体计算
     * 在balance这种worst case的情况下，每次一个recursion node会有四个子node
     * 由于worst case是balanced tree，所以height = logn
     * 等比公式前n项和S(n) = a1 * (1 - q^n) / 1 - q
     * time = 1 + 4 + 16 + ... = 4^0 + 4^1 + 4^2 + ... + 4^logn
     *      = (1 - 4^logn) / 1 - 4 = 4^logn = 2^2logn = 2^log(n^2) = n^2 = O(n^2)
     *
     * 空间复杂度O(logn)
     */
    public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        // Write your solution here
        if (one == null && two == null) {
            return true;
        } else if (one == null || two == null) {
            return false;
        } else if (one.key != two.key) {
            return false;
        }

        return (isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right)) ||
                (isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left));
    }
}
