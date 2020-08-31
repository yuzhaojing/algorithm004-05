package laioffer.CrossTrainingI;

import laioffer.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ClosestNumberInBSTII {

    public static void main(String[] args) {
    }

    /**
     * input: root   TreeNode
     *        target int
     *        k      int
     * output: int[] (在以root为根的BST中，离target最近的k个node的key)
     * 假设：root != null && k > 0
     * 如果不符合假设，那么这个tree是空的或者求的数个数无效，返回空数组
     *
     * high level: 使用sliding window的方法解答
     * mid level: BST的inOrder是升序的，可以在遍历过程中进行sliding window计算
     *  1、对root进行inOrder遍历
     *  2、遍历过程中向deque中add元素，如果不足k个直接加入，已经有k个的话，和第一个数进行比较，谁离target近取谁
     *  3、如果在某个node，deque中的数据已经有k个，并且不需要更新，那么返回这个int array
     *
     * time = O(n)
     * space = O(k)
     */
    public int[] closestKValues(TreeNode root, double target, int k) {
        // Write your solution here
        if (root == null || k < 1) {
            return new int[0];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        inOrder(root, target, k, deque);

        int size = deque.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = deque.pollFirst();
        }

        return res;
    }

    private void inOrder(TreeNode root, double target, int k, Deque<Integer> deque) {
        if (root == null) {
            return;
        }

        inOrder(root.left, target, k, deque);

        if (deque.size() < k) {
            deque.offerLast(root.key);
        } else {
            int key = deque.peekFirst();
            if (Math.abs(key - target) > Math.abs(root.key - target)) {
                deque.pollFirst();
                deque.offerLast(root.key);
            } else {
                return;
            }
        }

        inOrder(root.right, target, k, deque);
    }
}
