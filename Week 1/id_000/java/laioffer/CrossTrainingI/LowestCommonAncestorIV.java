package laioffer.CrossTrainingI;

import laioffer.tree.TreeNode;

import java.util.List;

public class LowestCommonAncestorIV {

    public static void main(String[] args) {
    }

    /**
     * input: root TreeNode, nodes List<TreeNode>
     * output: TreeNode (LCA of subset of set{nodes})
     * 假设：root != null && nodes != null && nodes.size() > 0
     * 如果不符合假设，那么树上没有节点或者求LCA的node不存在，返回null
     *
     * high level: 使用recursion方法解答
     * mid level: 在递归的时候，求{nodes}这个集合的子集在当前root下的情况
     *  1、向左右孩子要什么？ {nodes}的子集在左右孩子的情况
     *  2、当前层做什么？
     *     1.如果当前层的node在{nodes}的子集中，返回当前node
     *     2.如果左右子树不在{nodes}的子集中，返回null
     *     3.如果左右子树有一个在{nodes}的子集中，返回在子集中的node
     *     4.如果左右子树都在{nodes}的子集中，返回当前node
     *  3、向父节点返回什么？ merge在当前节点的操作中了
     *
     * time = O(n)
     * space = O(height)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
        // Write your solution here.
        if (root == null || nodes.contains(root)) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, nodes);
        TreeNode right = lowestCommonAncestor(root.right, nodes);

        if (left != null && right != null) {
            return root;
        }

        return left == null ? right : left;
    }
}
