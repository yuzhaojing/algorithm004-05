package laioffer.bfs;

import laioffer.tree.TreeNode;

import java.util.*;

public class LevelOrderTraversal {

    public static void main(String[] args) {

    }

    /**
     * Time = O(n)
     * 每个节点都遍历了一次，每次操作都是O(1)的
     *
     * Space = O(n)
     *
     * worst case为tree是complete的，那么最多缓存最后一层
     * 也就是n / 2个元素 O(n/2) -> O(n)
     */
    public List<List<Integer>> layerByLayer(TreeNode root) {
        // Write your solution here
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.key);
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            res.add(level);
        }

        return res;
    }
}
