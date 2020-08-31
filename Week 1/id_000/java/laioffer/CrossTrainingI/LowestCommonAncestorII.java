package laioffer.CrossTrainingI;

class TreeNodeP {
  public int key;
  public TreeNodeP left;
  public TreeNodeP right;
  public TreeNodeP parent;
  public TreeNodeP(int key, TreeNodeP parent) {
    this.key = key;
    this.parent = parent;
  }
}

public class LowestCommonAncestorII {

    public static void main(String[] args) {
    }

    /**
     * input: one, two TreeNode
     * output: TreeNode (LCA of subset of set{one, two})
     * 假设：one != null && two != null
     * 如果不符合假设，那么有一个node是空的，那么返回不为null的节点
     *
     * high level: 使用迭代的方法解答
     * mid level:
     *  1、先计算两个node到根节点的层数，如果根节点不一样，直接返回null。说明两个节点不在一棵树上
     *  2、将层数深的node向上移动到和另一个node相同的层数
     *  3、然后两个node同时向上走，直到走到同一个node，那么这个node就是LCA
     *
     * time = O(2n) = O(n)
     * space = O(1)
     */
    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
        // Write your solution here.
        if (one == null) {
            return two;
        }

        if (two == null) {
            return one;
        }

        Result oneResult = getDepth(one);
        Result twoResult = getDepth(two);

        if (oneResult.node != twoResult.node) {
            return null;
        }

        if (oneResult.depth > twoResult.depth) {
            return getLCA(one, two, oneResult.depth - twoResult.depth);
        } else {
            return getLCA(two, one, twoResult.depth - oneResult.depth);
        }
    }

    private TreeNodeP getLCA(TreeNodeP one, TreeNodeP two, int diff) {
        while (diff > 0) {
            one = one.parent;
            diff--;
        }

        while (one != two) {
            one = one.parent;
            two = two.parent;
        }

        return one;
    }

    private Result getDepth(TreeNodeP node) {
        int depth = 0;
        while (node.parent != null) {
            depth++;
            node = node.parent;
        }
        return new Result(node, depth);
    }

    class Result {
        TreeNodeP node;
        int depth;
        public Result(TreeNodeP node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
