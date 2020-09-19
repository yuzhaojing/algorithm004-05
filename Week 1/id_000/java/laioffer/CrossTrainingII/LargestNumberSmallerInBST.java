package laioffer.CrossTrainingII;

import laioffer.tree.ArrayToTree;
import laioffer.tree.TreeNode;

public class LargestNumberSmallerInBST {

    public static void main(String[] args) {
        String[] array = {"10","5","15","2","9","11","23","1","#","6","#","#","12","21","25"};
        TreeNode root = ArrayToTree.fromArrayToTree(array);
        System.out.println(new LargestNumberSmallerInBST().largestSmallerRecursion(root, 17));
    }

    /**
     * input: root TreeNode
     *        target int
     * output: int (在BST中离target最近的node的key)
     * 假设：root != null
     * 如果不符合假设，那么这棵树是空的，返回Integer.MIN_VALUE
     *
     * high level: 可以使用recursion进行解答
     * mid level:
     *  1、向左右孩子要什么？ 要比target小的最大node的key
     *  2、在本层做什么？
     *     1. if (root.key == key) recursion(root.left, target)
     *     2. else if (root.key < key)
     *        return recursion(root.right, target) == Integer.MIN_VALUE ? root.key : recursion(root.right, target)
     *     3. else recursion(root.left, target)
     *
     * time = O(n)
     * space = O(height)
     */
    public int largestSmallerRecursion(TreeNode root, int target) {
        // Write your solution here
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        if (root.key < target) {
            int res = largestSmallerRecursion(root.right, target);
            return res == Integer.MIN_VALUE ? root.key : res;
        } else {
            return largestSmallerRecursion(root.left, target);
        }
    }
}
