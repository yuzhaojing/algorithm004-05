package laioffer.RecursionIII;

import laioffer.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReconstructBinaryTreeWithLevelorderAndInorder {

    public static void main(String[] args) {

    }

    /**
     * input: inOrder  int[]
     *        levelOrder int[]
     * output: TreeNode
     * 假设：inOrder != null && inOrder.length > 0 &&
     *      levelOrder != null && levelOrder.length > 0
     *      不能有重复元素
     * 如果不符合假设，那么这棵树是空的，返回null;
     *
     * high level: 找出root节点，然后将数组分为左子树和右子树，进行递归，返回各自的root
     * mid level:
     *  1、根据levelOrder的性质，第一个一定是root，所以根据levelOrder的第一个元素作为root
     *  2、然后查到这个元素在inOrder中的角标，之后遍历levelOrder，角标大于root的都是右子树，小于root的都是左子树
     *  3、将其分别放入两个list中，递归两次，返回值给root.left和root.right
     *
     * time = avg: O(nlogn)
     *        worst: O(n^2)
     * space = avg: O(n)
     *         worst: O(n^2)
     */
    public TreeNode reconstruct(int[] inOrder, int[] levelOrder) {
        // Write your solution here
        if (inOrder == null || inOrder.length == 0 || levelOrder == null || levelOrder.length == 0) {
            return null;
        }

        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            indexMap.put(inOrder[i], i);
        }
        List<Integer> level = new ArrayList<>();
        for (int num : levelOrder) {
            level.add(num);
        }

        return helper(level, indexMap);
    }

    private TreeNode helper(List<Integer> levelOrder, Map<Integer, Integer> indexMap) {
        if (levelOrder.size() == 0) {
            return null;
        }

        TreeNode root = new TreeNode(levelOrder.remove(0));
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int num : levelOrder) {
            if (indexMap.get(num) < indexMap.get(root.key)) {
                left.add(num);
            } else {
                right.add(num);
            }
        }

        root.left = helper(left, indexMap);
        root.right = helper(right, indexMap);
        return root;
    }
}
