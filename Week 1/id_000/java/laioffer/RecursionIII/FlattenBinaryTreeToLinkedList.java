package laioffer.RecursionIII;

import laioffer.tree.TreeNode;

public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {

    }

    /**
     * input: root TreeNode
     * output: TreeNode
     * 假设：root != null
     * 如果不符合假设，那么这棵树是空的，返回null;
     *
     * high level: 使用recursion解答
     * mid level:
     *  1、使用一个变量记录上一个节点
     *  2、如果上一个节点为null，将本节点赋值，否则将本节点接到上一个节点的右子树上
     *  3、更新上一个节点，然后递归
     *
     * time = O(n)
     * space = O(height)
     */
    public TreeNode flatten(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return null;
        }

        TreeNode[] prev = new TreeNode[1];
        helperInOrder(root, prev);
        return root;
    }

    private void helperInOrder(TreeNode root, TreeNode[] prev) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        if (prev[0] == null) {
            prev[0] = root;
        } else {
            prev[0].right = root;
            prev[0] = root;
        }

        // 由于只接到右子树上，如果不将左子树清空，那么左子树将会保留
        root.left = null;

        helperInOrder(left, prev);
        helperInOrder(right, prev);
    }

    private void helperPostOrder(TreeNode root, TreeNode[] prev) {
        if (root == null) {
            return;
        }

        helperPostOrder(root.right, prev);
        helperPostOrder(root.left, prev);
        root.right = prev[0];
        root.left = null;
        prev[0] = root;
    }
}
