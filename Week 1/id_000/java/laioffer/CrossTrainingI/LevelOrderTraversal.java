package laioffer.CrossTrainingI;

import laioffer.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public static void main(String[] args) {

    }

    /**
     * input: root TreeNode
     * output: List<Integer> (按题目顺序遍历node的key)
     * 假设：root != null
     * 如果不符合假设，那么root为null，返回空list
     *
     * high level: 使用BFS1进行解答
     * mid level: 在遍历的时候，每层开始的时候记录queue中的元素数量，处理完之后进入下一层
     *  1、每层只处理开始的时候记录的数量
     *  2、处理每个node的时候，先放left，再放right
     *
     * time = O(n)
     * space = O(n)
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
