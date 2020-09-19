package laioffer.CrossTrainingII;

import laioffer.tree.ArrayToTree;
import laioffer.tree.TreeNode;

public class ClosestNumberInBST {

    public static void main(String[] args) {
        String[] array = {"10","5","15","2","9","11","23","1","#","6","#","#","12","21","25"};
        TreeNode root = ArrayToTree.fromArrayToTree(array);
        System.out.println(new ClosestNumberInBST().closestIterator(root, 17));
    }

    /**
     * input: root TreeNode
     *        target int
     * output: int (在BST中离target最近的node的key)
     * 假设：root != null
     * 如果不符合假设，那么这棵树是空的，返回值需要商议，暂定返回Integer.MIN_VALUE
     *
     * high level: 可以使用recursion进行解答
     * mid level:
     *  1、向左右孩子要什么？ 要离target最近的node的key
     *  2、在本层做什么？
     *     1. if (root.key == key) return root.key
     *     2. else if (root.key < key)
     *        return Min(return Math.abs(root.key - key), Math.abs(recursion(root.right, target)))
     *     3. else
     *        return Min(return Math.abs(root.key - key), Math.abs(recursion(root.left, target)))
     *
     * time = O(n)
     * space = O(height)
     */
    public int closestRecursion(TreeNode root, int target) {
        // Write your solution here
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        // 初始值必须为root.key,因为res有可能不会进行后续赋值
        // 如果不进入两个if的话，res不为root.key会出现错误
        int res = root.key;

        if (root.key < target && root.right != null) {
            res = closestRecursion(root.right, target);
        } else if (root.key > target && root.left != null) {
            res = closestRecursion(root.left, target);
        }

        return Math.abs(root.key - target) < Math.abs(res - target) ? root.key : res;
    }

    public int closestIterator(TreeNode root, int target) {
        // Write your solution here
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        int res = root.key;

        while (root != null) {
            if (root.key == target) {
                res = root.key;
                return res;
            } else if (root.key < target) {
                res = Math.abs(res - target) < Math.abs(root.key - target) ? res : root.key;
                root = root.right;
            } else {
                res = Math.abs(res - target) < Math.abs(root.key - target) ? res : root.key;
                root = root.left;
            }
        }

        return res;
    }
}
