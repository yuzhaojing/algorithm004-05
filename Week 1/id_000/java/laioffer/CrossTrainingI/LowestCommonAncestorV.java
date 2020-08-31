package laioffer.CrossTrainingI;

import laioffer.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

class KnaryTreeNode {
    int key;
    List<KnaryTreeNode> children;
    public KnaryTreeNode(int key) {
        this.key = key;
        this.children = new ArrayList<>();
    }
}

public class LowestCommonAncestorV {

    public static void main(String[] args) {
    }

    /**
     * input: root, a, b KnaryTreeNode
     * output: TreeNode (LCA of subset of set{a, b})
     * 假设：root != null
     * 如果不符合假设，那么这个tree是空的，也就没有LCA
     *
     * high level: 使用recursion方法解答
     * mid level: 在递归的时候，求{a, b}这个集合的子集在当前root下的情况
     *  1、向孩子们要什么？ {a, b}的子集在孩子们下的情况
     *  2、当前层做什么？
     *     1.如果当前层的node在{a, b}的子集中，返回当前node
     *     2.如果孩子不在{one, two}的子集中，返回null
     *     3.如果孩子有一个在{one, two}的子集中，返回在子集中的node
     *     4.如果孩子有两个或以上都在{one, two}的子集中，返回当前node
     *  3、向父节点返回什么？ merge在当前节点的操作中了
     *
     * time = O(n)
     * space = O(height)
     */
    public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
        // Write your solution here
        if (root == null || root == a || root == b) {
            return root;
        }

        KnaryTreeNode res = null;
        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode node = lowestCommonAncestor(child, a, b);
            if (node != null) {
                if (res != null) {
                    return root;
                }
                res = node;
            }
        }

        return res;
    }
}
