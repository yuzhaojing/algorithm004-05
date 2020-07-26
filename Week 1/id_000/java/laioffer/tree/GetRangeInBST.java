package laioffer.tree;

import java.util.ArrayList;
import java.util.List;

public class GetRangeInBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(20);

        System.out.println(new GetRangeInBST().getRange(root, 10, 16));
    }

    /**
     * 时间复杂度O(n)
     * worst case: 确实是一个BST，每个节点遍历一次
     *
     * 空间复杂度O(height)
     */
    public List<Integer> getRange(TreeNode root, int min, int max) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        getRange(root, min, max, res);
        return res;
    }

    private void getRange(TreeNode root, int min, int max, List<Integer> res) {
        if (root == null) {
            return;
        }

        if (root.key > min) {
            getRange(root.left, min, max, res);
        }

        if (root.key >= min && root.key <= max) {
            res.add(root.key);
        }

        if (root.key < max) {
            getRange(root.right, min, max, res);
        }
    }
}
