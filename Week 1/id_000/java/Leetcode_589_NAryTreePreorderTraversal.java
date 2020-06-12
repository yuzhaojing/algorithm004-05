import java.util.ArrayList;
import java.util.List;

public class Leetcode_589_NAryTreePreorderTraversal {

    public static void main(String[] args) {

    }


    private static List<Integer> method1(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        preOrder(res, root);
        return res;
    }

    private static void preOrder(List<Integer> res, Node root) {
        if (root != null) {
            res.add(root.val);
            for (Node child : root.children) {
                preOrder(res, child);
            }
        }
    }
}
