package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Leetcode_145_BinaryTreePostorderTraversal {

    public static void main(String[] args) {

    }

    // 递归
    private static List<Integer> method1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        postorder(res, root);
        return res;
    }

    private static void postorder(List<Integer> res, TreeNode root) {
        if (root != null) {
            postorder(res, root.left);
            postorder(res, root.right);
            res.add(root.val);
        }
    }

    // 节点标记
    private static List<Integer> method2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<Object> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Object pop = stack.pop();
            if (pop instanceof TreeNode) {
                stack.push(((TreeNode) pop).val);
                stack.push(((TreeNode) pop).right);
                stack.push(((TreeNode) pop).left);
            } else if (pop instanceof Integer) {
                res.add((Integer) pop);
            }
        }

        return res;
    }

}
