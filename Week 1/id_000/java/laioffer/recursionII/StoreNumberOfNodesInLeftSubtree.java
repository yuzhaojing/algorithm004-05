package laioffer.recursionII;

class TreeNodeLeft {
    public int key;
    public TreeNodeLeft left;
    public TreeNodeLeft right;
    public int numNodesLeft;
    public TreeNodeLeft(int key) {
        this.key = key;
    }
}

public class StoreNumberOfNodesInLeftSubtree {


    public static void main(String[] args) {
        TreeNodeLeft treeNodeLeft = new TreeNodeLeft(1);
        new StoreNumberOfNodesInLeftSubtree().numNodesLeft(treeNodeLeft);
    }

    public void numNodesLeft(TreeNodeLeft root) {
        // Write your solution here
        numNodes(root);
    }

    private int numNodes(TreeNodeLeft root) {
        if (root == null) {
            return 0;
        }

        int leftNodes = numNodes(root.left);
        int rightNodes = numNodes(root.right);

        root.numNodesLeft = leftNodes;
        return leftNodes + rightNodes + 1;
    }
}
