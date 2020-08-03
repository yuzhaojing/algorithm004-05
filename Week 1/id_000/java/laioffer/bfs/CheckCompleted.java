package laioffer.bfs;

import laioffer.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CheckCompleted {

    public static void main(String[] args) {

    }

    /**
     * time = O(n)
     * 每个node遍历一次
     *
     * space = O(n/2) = O(n)
     * worst case 树是完全二叉树，缓存全部的叶子节点，即n/2个node
     */
    public boolean isCompleted(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        // 标志位，如果flag=true之后还出现了node，就说明不是complete tree
        boolean flag = false;

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left == null) {
                // 如果有子节点为null，将flag赋值为true
                flag = true;
            } else if (flag) {
                // 如果子节点不是null，查看之前是否有null
                return false;
            } else {
                // 子节点不是null，并且之前没有null
                // 将子节点放入队列中
                queue.offer(cur.left);
            }

            if (cur.right == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(cur.right);
            }
        }

        return true;
    }
}
