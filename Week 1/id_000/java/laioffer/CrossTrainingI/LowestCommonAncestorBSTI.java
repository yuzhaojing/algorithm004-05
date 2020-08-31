package laioffer.CrossTrainingI;

import laioffer.tree.TreeNode;

public class LowestCommonAncestorBSTI {

    public static void main(String[] args) {
        System.out.println(new LowestCommonAncestorBSTI().lowestCommonAncestor(new TreeNode(1), new TreeNode(2), new TreeNode(3)));
    }

    /**
     * input: root TreeNode; a,b int
     * output: TreeNode (LCA of subset of set{nodes})
     * 假设：root != null && (p && q under root)
     * 如果不符合假设，那么树上没有节点或者求LCA的node不存在，返回null
     *
     * high level: 使用recursion方法解答
     * mid level: 在递归的时候，查看root的key和p、q之的关系，当root的key处于p和q之间的时候，返回结果
     *  1、如果root.key同时小于p和q，那么往右子树递归
     *  2、如果root.key同时大于p和q，那么往左子树递归
     *
     * time = O(logN)
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
