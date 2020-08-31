package laioffer.CrossTrainingI;

import laioffer.tree.TreeNode;

public class LowestCommonAncestorI {

    public static void main(String[] args) {
        System.out.println(new LowestCommonAncestorI().lowestCommonAncestor(new TreeNode(1), new TreeNode(2), new TreeNode(3)));

        double a = 1.1;
        int b = 2;
    }

    /**
     * input: root, one, two TreeNode
     * output: TreeNode (LCA of subset of set{one, two})
     * 假设：root != null
     * 如果不符合假设，那么这个tree是空的，也就没有LCA
     *
     * high level: 使用recursion方法解答
     * mid level: 在递归的时候，求{one, two}这个集合的子集在当前root下的情况
     *  1、向左右孩子要什么？ {one, two}的子集在左右孩子的情况
     *  2、当前层做什么？
     *     1.如果当前层的node在{one, two}的子集中，返回当前node
     *     2.如果左右子树不在{one, two}的子集中，返回null
     *     3.如果左右子树有一个在{one, two}的子集中，返回在子集中的node
     *     4.如果左右子树都在{one, two}的子集中，返回当前node
     *  3、向父节点返回什么？ merge在当前节点的操作中了
     *
     * time = O(n)
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
