package laioffer.tree;

import laioffer.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeTravrse {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        TreeTravrse travrse = new TreeTravrse();

        System.out.println(travrse.preOrder(root));
        System.out.println(travrse.inOrder(root));
        System.out.println(travrse.postOrder(root));
    }

    public List<Integer> preOrder(TreeNode root) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    private void preOrder(TreeNode root, List<Integer> res) {
        if (root != null) {
            res.add(root.key);
            preOrder(root.left, res);
            preOrder(root.right, res);
        }
    }

    public List<Integer> inOrder(TreeNode root) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    private void inOrder(TreeNode root, List<Integer> res) {
        if (root != null) {
            preOrder(root.left, res);
            res.add(root.key);
            preOrder(root.right, res);
        }
    }

    public List<Integer> postOrder(TreeNode root) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    private void postOrder(TreeNode root, List<Integer> res) {
        if (root != null) {
            preOrder(root.left, res);
            preOrder(root.right, res);
            res.add(root.key);
        }
    }
}
