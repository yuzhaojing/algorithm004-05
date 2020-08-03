package laioffer.tree;

import java.util.*;

public class RightViewOfTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.right.right = new TreeNode(7);

        System.out.println(new RightViewOfTree().rightViewDFS(root));
    }

    /**
     * BFS-1 广度优先搜索
     */
    private List<Integer> rightView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }

                if (cur.right != null) {
                    queue.offer(cur.right);
                }

                if (i == size - 1) {
                    res.add(cur.key);
                }
            }
        }

        return res;
    }

    /**
     * DFS 反向preOrder，root -> right -> left
     * 记录当前层，每次到达新的一层就把当前元素放入结果集
     */
    private List<Integer> rightViewDFS(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        rightViewDFS(root, new int[1], 1, res);
        return res;
    }

    private void rightViewDFS(TreeNode root, int[] visited, int level, List<Integer> res) {
        if (root == null) {
            return;
        }

        if (visited[0] < level) {
            visited[0] = level;
            res.add(root.key);
        }

        rightViewDFS(root.right, visited, level + 1, res);
        rightViewDFS(root.left, visited, level + 1, res);
    }
}
