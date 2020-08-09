package laioffer.recursionII;

import laioffer.tree.TreeNode;

public class LowestCommonAncestorI {

    public static void main(String[] args) {
        System.out.println(new LowestCommonAncestorI().lowestCommonAncestor(new TreeNode(1), new TreeNode(2), new TreeNode(3)));
    }

    /**
     * 分为两种情况
     *
     * 两个目标node是直属关系
     * case1 left == null && right == null return null
     * case2 left == null || right == null return not null one
     * case3 left != null && right != null 在直属关系情况下不可能出现
     *
     * 两个目标不是直属关系
     * case1 left == null && right == null return null
     * case2 left == null || right == null return not null one
     * case3 left != null && right != null return root
     *
     * 因为第二种情况的判断条件囊括了第一种情况，所以可以写第二种就行
     *
     * 注意: 如果one和two不一定都存在，则需要进一步判断
     * 1、通过lowestCommonAncestor返回找到的公共祖先
     * 2、如果返回的node != one && node != two，那么这两个node一定都存在
     *    因为只有两个node都找到了才可能返回root
     * 3、如果返回的node == one || node == two，那么再根据返回的node为root
     *    找一次另一个node的公共祖先，如果返回null，说明不存在。否则就是存在
     *
     * time = O(n)
     *
     * space = O(height)
     */
    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode one, TreeNode two) {
        // Write your solution here.
        // 当 root == null 或者找到了目标node的时候，将其返回
        if (root == null || root == one || root == two) {
            return root;
        }

        // 分别在左子树和右子树中寻找目标节点
        TreeNode left = lowestCommonAncestor(root.left, one, two);
        TreeNode right = lowestCommonAncestor(root.right, one, two);

        // 如果两个都不为null，说明左子树和右子树各找到一个，则返回当前节点作为公共祖先
        if (left != null && right != null) {
            return root;
        }

        // 否则返回不为null的节点，如果都为null，则随意返回
        return left == null ? right : left;
    }
}
