package laioffer.Exam.Final;

import laioffer.tree.ArrayToTree;
import laioffer.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTreeCousins {

    public static void main(String[] args) {
        TreeNode root = ArrayToTree.fromArrayToTree(new String[]{"6", "3", "5", "7", "8", "1", "2"});

        System.out.println(new BinaryTreeCousins().cousinsInBinaryTree(root, root.left.left, root.right.left));
        System.out.println(new BinaryTreeCousins().cousinsInBinaryTree(root, root.left, root.right));
        System.out.println(new BinaryTreeCousins().cousinsInBinaryTree(root, root.left.left, root.right));
    }

    public boolean cousinsInBinaryTree(TreeNode root, TreeNode one, TreeNode two) {
        if (root == one || root == two || one == null || two == null) {
            return false;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();

                // 排除one和two是同一个parent的情况
                if ((cur.left == one && cur.right == two) || (cur.left == two && cur.right == one)) {
                    return false;
                }

                if (cur.left != null) {
                    if (cur.left == one || cur.left == two) {
                        count++;
                    }

                    queue.offer(cur.left);
                }

                if (cur.right != null) {
                    if (cur.right == one || cur.right == two) {
                        count++;
                    }

                    queue.offer(cur.right);
                }
            }

            if (count == 1) {
                return false;
            }

            if (count == 2) {
                return true;
            }
        }

        return false;
    }

    public boolean isCousinsDFS(TreeNode root, TreeNode one, TreeNode two) {
        if (root == one || root == two || one == null || two == null) {
            return false;
        }

        boolean[] isCousins = new boolean[1];
        helper(root, one, two, 0, isCousins);
        return isCousins[0];
    }

    private int helper(TreeNode root, TreeNode one, TreeNode two, int level, boolean[] isCousins) {
        if (root == null) {
            return 0;
        }

        if (root == one || root == two) {
            return level;
        }

        // 对于root，如果本身不是目标node，只有两种可能
        // 1、两个目标node不是直属关系，这种只需要对两边分别找到高度，
        //    直到找到两个目标node分叉的root，然后对比两边高度差就可以了
        // 2、两个目标node在一边，那么在找到第一个node的时候就返回了，另一边是0，
        //    所以left != right，也可以得到答案
        int left = helper(root.left, one, two, level + 1, isCousins);
        int right = helper(root.right, one, two, level + 1, isCousins);

        if (left == right && left > level + 1) {
            isCousins[0] = true;
        }

        return left == 0 ? right : left;
    }

    public boolean isCousinsBFS(TreeNode root, TreeNode one, TreeNode two) {
        if (root == one || root == two || one == null || two == null) {
            return false;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();

                if ((cur.left == one && cur.right == two)
                        || (cur.left == two && cur.right == one)) {
                    return false;
                }

                if (cur.left == one || cur.left == two) {
                    count++;
                }

                if (cur.right == one || cur.right == two) {
                    count++;
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }

                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            if (count == 1) {
                return false;
            }

            if (count == 2) {
                return true;
            }
        }

        return false;
    }

    /**
     * input:  TreeNode root
     *         TreeNode one
     *         TreeNode two
     * output: boolean
     * Assume: one != null && two != null
     *
     * high level: 使用recursion解答
     * detail level:
     *  1、计算从one和two到root需要的level，如果level不同return false
     *  2、如果level相同比较parent，parent不同return false
     *
     * time = 2n = O(n)
     * space = O(height)
     */
    public boolean isCousinsRecursion(TreeNode root, TreeNode one, TreeNode two) {
        // 无法确定是哪个null，默认返回false
        if (one == root || two == root || one == null || two == null) {
            return false;
        }

        int depthOne = getDepth(root, one);
        int depthTwo = getDepth(root, two);

        if (depthOne != depthTwo) {
            return false;
        }

        TreeNode oneParent = getParent(root, one);
        TreeNode twoParent = getParent(root, two);

        return oneParent != twoParent;
    }

    private TreeNode getParent(TreeNode root, TreeNode node) {
        if (root == null) {
            return null;
        }

        if (root.left == node || root.right == node) {
            return root;
        }

        TreeNode left = getParent(root.left, node);
        TreeNode right = getParent(root.right, node);

        return left == null ? right : left;
    }

    private int getDepth(TreeNode root, TreeNode node) {
        if (root == null) {
            return 0;
        }

        if (root.left == node || root.right == node) {
            return 1;
        }

        int left = getDepth(root.left, node);
        int right = getDepth(root.right, node);

        if (left == 0 && right == 0) {
            return 0;
        } else {
            return left == 0 ? right + 1 : left + 1;
        }
    }
}
