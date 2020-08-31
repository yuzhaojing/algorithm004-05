package laioffer.CrossTrainingI;

import java.util.List;

public class LowestCommonAncestorVI {

    public static void main(String[] args) {
    }

    public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
        // Write your solution here
        if (root == null || nodes.contains(root)) {
            return root;
        }

        KnaryTreeNode res = null;
        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode node = lowestCommonAncestor(child, nodes);
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
