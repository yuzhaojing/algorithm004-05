package laioffer.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class ArrayToTree {

    public static void main(String[] args) {
        String[] array = {"1", "2", "#", "3", "4", "#", "5", "6"};
        TreeNode root = fromArrayToTree(array);
    }

    public static TreeNode fromArrayToTree(String[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(array[0]));

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int startIndex = 1;
        int nodeItems = 2;

        while (startIndex < array.length) {
            for (int i = startIndex; i < startIndex + nodeItems; i = i + 2) {
                if (i == array.length) {
                   return root;
                }

                TreeNode node = queue.poll();

                if (!array[i].equals("#")) {
                    node.left = new TreeNode(Integer.parseInt(array[i]));
                    queue.offer(node.left);
                }

                if (i + 1 == array.length) {
                    return root;
                }

                if (!array[i + 1].equals("#")) {
                    node.right = new TreeNode(Integer.parseInt(array[i + 1]));
                    queue.offer(node.right);
                }
            }

            startIndex += nodeItems;
            nodeItems = queue.size() * 2;
        }

        return root;
    }
}
