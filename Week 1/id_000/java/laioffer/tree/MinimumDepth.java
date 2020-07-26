package laioffer.tree;

public class MinimumDepth {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);

        System.out.println(new MinimumDepth().minDepth(root));
    }

    /**
     * time = O(n)
     *
     * space = O(height)
     */
    public int minDepth(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return 0;
        }

        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);

        // 左右节点只要有一个为null，就不能比较最小值
        // 因为题目要求的是到叶节点的最小值，而null不算叶节点
        // 此时leftMinDepth和leftMinDepth至少有一个为0
        // 如果只有一个为0，返回的是另一个节点的最小深度+1，符合预期
        // 如果都为0，说明root就是叶节点，返回1符合预期
        if (root.left == null || root.right == null) {
            return leftMinDepth + rightMinDepth + 1;
        }

        return Math.min(leftMinDepth, rightMinDepth) + 1;
    }
}
