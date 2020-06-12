import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Leetcode_95_BinaryTreeInorderTraversal {

    public static void main(String[] args) {

    }

    // 递归
    private static List<Integer> method1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(res, root);
        return res;
    }

    private static void inorder(List<Integer> res, TreeNode root) {
        if (root != null) {
            inorder(res, root.left);
            res.add(root.val);
            inorder(res, root.right);
        }
    }

    // 迭代
    private static List<Integer> method2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }

        return res;
    }

    // 节点标记法
    private static List<Integer> method3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<Object> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            Object pop = stack.pop();
            if (pop instanceof TreeNode) {
                stack.push(((TreeNode) pop).right);
                stack.push(((TreeNode) pop).val);
                stack.push(((TreeNode) pop).left);
            } else if (pop instanceof Integer){
                res.add((Integer) pop);
            }
        }

        return res;
    }

}
