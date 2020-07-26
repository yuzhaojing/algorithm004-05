package laioffer.tree;

public class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.key = value;
    }
}

/**
 * 平衡二叉树 balance binary tree
 *
 * 定义: 对于任意一个节点，左右子树高度差 <= 1
 *
 * example:
 *                    10
 *                  /    \
 *                 5      15
 *                / \    /  \
 *               2 null null null
 */

/**
 * 完全二叉树 complete binary tree
 *
 * 定义: 除了最后一层的叶子节点外，必须全部填满，最后一层需要尽可能往左边靠拢
 *
 * example:
 *                    10
 *                  /    \
 *                 5      15          yes
 *                / \    /  \
 *               2 null null null
 *
 *                    10
 *                  /    \
 *                 5      15           no
 *                / \    /  \
 *               2 null 8 null
 */

/**
 * 二叉搜索树 binary search tree
 *
 * 定义: 对于任意一个节点，它的左子树小于该节点，它的右子树大于该节点
 *
 * example:
 *                    10
 *                  /    \
 *                 5      15          yes
 *                / \    /  \
 *               2 null 12 null
 *
 *                    10
 *                  /    \
 *                 5      15          no（11 > 10）
 *                / \    /  \
 *               2 null 11 null
 */
