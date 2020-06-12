import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

public class Leetcode_429_NAryTreeLevelOrderTraversal {

    public static void main(String[] args) {

    }


    private static List<List<Integer>> method1(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                    if (node.children != null) {
                        queue.addAll(node.children);
                    }
                }
            }

            res.add(list);
        }

        return res;
    }
}
