package laioffer.RecursionIII;

import laioffer.tree.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReconstructBSTWithPostorderTraversal {

    public static void main(String[] args) {

    }

    /**
     * input: postOrder  int[]
     * output: TreeNode
     * 假设：postOrder != null && postOrder.length > 0
     *      不能有重复元素
     * 如果不符合假设，那么这棵树是空的，返回null;
     *
     * high level:
     * mid level:
     *  1、从后往前遍历postOrder数组，最后一个元素必然是root
     *  2、由于是BST，可以根据root的key，来判断下面的元素是位于root的左子树还是root的右子树
     *  3、后续遍历的特点为最后一个元素为root，前n个元素为左子树元素，左子树元素与root之间的元素是有子树元素
     *
     * time = O(n)
     * space = O(height + n)
     */
    public TreeNode reconstruct(int[] post) {
        // Write your solution here
        if (post == null || post.length == 0) {
            return null;
        }

        int[] index = new int[] {post.length - 1};
        return helper(post, index, Integer.MIN_VALUE);
    }

    // 这里必须用min来限制右子树，因为root左边是右子树
    // 只有限制了右子树，才能知道左子树的index在什么地方
    private TreeNode helper(int[] post, int[] index, int min) {
        if (index[0] < 0 || post[index[0]] <= min) {
            return null;
        }

        TreeNode root = new TreeNode(post[index[0]--]);
        root.right = helper(post, index, root.key);
        root.left = helper(post, index, min);
        return root;
    }
}
