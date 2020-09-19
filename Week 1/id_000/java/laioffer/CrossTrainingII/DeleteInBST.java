package laioffer.CrossTrainingII;

import laioffer.practice.design_queue_stack_deque.ListNode;
import laioffer.tree.TreeNode;

public class DeleteInBST {

    public static void main(String[] args) {

    }

    /**
     * input: root TreeNode
     * output: TreeNode (删除指定node后的root)
     * 假设：root != null
     * 如果不符合假设，那么这棵树是空的，返回null
     *
     * high level: 可以使用recursion进行解答
     * mid level:
     *  1、向左右孩子要什么？ 要删除给定node之后的root
     *  2、在本层做什么？
     *     1. if (root.key == key) 将左子树中最大的，或者右子树中最小的移动过来当root，并返回该node
     *     2. else if (root.key < key) root.right = delete(root.right, key)
     *     3. else root.left = delete(root.left, key)
     *
     * time = O(n)
     * space = O(height)
     */
    public TreeNode deleteTree(TreeNode root, int key) {
        // Write your solution here
        if (root == null) {
            return null;
        }

        if (root.key < key) {
            root.right = deleteTree(root.right, key);
            return root;
        } else if (root.key > key) {
            root.left = deleteTree(root.left, key);
            return root;
        }

        // 需要删除的是root,需要找到顶替root的节点
        // 1.root.left == null || root.right == null
        //   return the child is not null
        // 2.root.left == null && root.right == null
        //   return null
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }

        // 3.root.right.left == null
        // root.right.left = root.left; return root.right
        if (root.right.left == null) {
            root.right.left = root.left;
            return root.right;
        }

        // 4.root.right.left != null
        // 找到右子树中最小的移动过来当root，并返回该node
        TreeNode newRoot = getSmallestNode(root.right);
        newRoot.left = root.left;
        newRoot.right = root.right;
        return newRoot;
    }

    private TreeNode getSmallestNode(TreeNode node) {
        TreeNode prev = node;
        TreeNode cur = node.left;
        while (cur.left != null) {
            prev = cur;
            cur = cur.left;
        }
        prev.left = cur.right;
        return cur;
    }
}
