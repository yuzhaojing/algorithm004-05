package laioffer.tree;

public class LowestCommonAncestorII {

    public static void main(String[] args) {
        TreeNodeP root = new TreeNodeP(8, null);
        root.left = new TreeNodeP(5, root);
        root.right = new TreeNodeP(13, root);
        root.left.left = new TreeNodeP(3, root.left);
        root.left.right = new TreeNodeP(6, root.left);
        root.right.left = new TreeNodeP(9, root.right);
        root.right.right = new TreeNodeP(17, root.right);

        TreeNodeP node = new LowestCommonAncestorII().lowestCommonAncestor(root.left.left, root.right);
    }

    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
        // Write your solution here.
        // 只要有一个输入的node == null，那么可以直接返回另外一个node
        // 因为任何一棵树都有null节点，所以任意一个非空的节点都是该节点与node的最近公共祖先
        if (one == null) {
            return two;
        }

        if (two == null) {
            return one;
        }

        // 计算两个node距离root的距离以及root节点
        // 保存root节点是用于比较one和two是否在同一颗树上
        Result resultOne = getDepth(one);
        Result resultTwo = getDepth(two);

        // 如果两个node的root不是一个节点，证明这两个node不在同一颗树上
        if (resultOne.node != resultTwo.node) {
            return null;
        }

        int depthOne = resultOne.depth;
        int depthTwo = resultTwo.depth;

        // 如果depthOne > depthTwo，说明one和two不在同一层上
        // 先将one和two移动到同一层上，然后如果不是一个node，
        // 各自走到父节点，知道one == two，返回该节点
        if (depthOne > depthTwo) {
            return getLCA(one, two, depthOne - depthTwo);
        } else {
            return getLCA(two, one, depthTwo - depthOne);
        }
    }

    private TreeNodeP getLCA(TreeNodeP lowerNode, TreeNodeP higherNode, int diffDepth) {
        while (diffDepth > 0) {
            lowerNode = lowerNode.parent;
            diffDepth--;
        }

        while (lowerNode != higherNode) {
            lowerNode = lowerNode.parent;
            higherNode = higherNode.parent;
        }

        return lowerNode;
    }

    private Result getDepth(TreeNodeP node) {
        int depth = 1;
        // node != null，在之前的base case中判断过了
        while (node.parent != null) {
            node = node.parent;
            depth++;
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
