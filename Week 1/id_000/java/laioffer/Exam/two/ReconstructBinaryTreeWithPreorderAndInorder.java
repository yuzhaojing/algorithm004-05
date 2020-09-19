package laioffer.Exam.two;

import laioffer.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ReconstructBinaryTreeWithPreorderAndInorder {

    public static void main(String[] args) {

    }

    /**
     * input: inOrder  int[]
     *        preOrder int[]
     * output: TreeNode
     * 假设：inOrder != null && inOrder.length > 0 &&
     *      preOrder != null && preOrder.length > 0
     *      不能有重复元素
     * 如果不符合假设，那么这棵树是空的，返回null;
     *
     * high level:
     * mid level:
     *  1、根据preOrder的第一个node，也就是root，找到root在inOrder中的位置。
     *     那么inOrder数组中在root左边的就是左子树，在root右边的就是右子树
     *  2、在preOrder中root的下一个node就是左子树的root。
     *     计算root左子树个数，在preOrder中跳过相同个数的node，那么下一个node就是右子树的root
     *
     * time = O(n)
     * space = O(n + height)
     * 使用n的空间存储中序遍历
     * 递归height层
     */
    public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
        // Write your solution here
        if (inOrder == null || inOrder.length == 0 || preOrder != null && preOrder.length == 0) {
            return null;
        }

        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            indexMap.put(inOrder[i], i);
        }

        return helper(inOrder, 0, inOrder.length - 1, preOrder, 0, preOrder.length - 1, indexMap);
    }

    private TreeNode helper(int[] inOrder, int inLeft, int inRight, int[]preOrder, int preLeft, int preRight, Map<Integer, Integer> indexMap) {
        if (inLeft > inRight) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[preLeft]);
        int rootIndex = indexMap.get(preOrder[preLeft]);

        root.left =  helper(inOrder, inLeft, rootIndex - 1, preOrder, preLeft + 1, preLeft + rootIndex - inLeft, indexMap);
        root.right = helper(inOrder, rootIndex + 1, inRight, preOrder, preLeft + rootIndex - inLeft + 1, preRight, indexMap);

        return root;
    }
}
