package laioffer.CrossTrainingI;

import laioffer.tree.TreeNode;

import java.util.*;

public class LevelZigZagOrderTraversal {

    public static void main(String[] args) {

    }

    /**
     * input: root TreeNode
     * output: List<Integer> (按题目顺序遍历node的key)
     * 假设：root != null
     * 如果不符合假设，那么root为null，返回空list
     * high level: 使用BFS1进行解答
     * mid level: 在遍历的时候，计算每层是奇数层还是偶数层，对不同层遍历顺序不同
     *  1、永远维持deque中的元素是从左到右排列的
     *  2、如果是奇数层，右进左出，先放右子树，再放左子树
     *  3、如果是偶数层，左进右出，先放左子树，再放右子树
     *  4、如果当前层是从左开始遍历，那么先放左子树，从右开始遍历，先放右子树
     * time = O(N)
     * space = O(N)
     */
    public List<Integer> zigZag(TreeNode root) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);
        int level = 1;

        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = null;
                if (level % 2 == 0) {
                    cur = deque.pollLast();
                    if (cur.left != null) {
                        deque.offerFirst(cur.left);
                    }
                    if (cur.right != null) {
                        deque.offerFirst(cur.right);
                    }
                } else {
                    cur = deque.pollFirst();
                    if (cur.right != null) {
                        deque.offerLast(cur.right);
                    }
                    if (cur.left != null) {
                        deque.offerLast(cur.left);
                    }
                }
                res.add(cur.key);
            }
            level += 1;
        }

        return res;
    }
}
