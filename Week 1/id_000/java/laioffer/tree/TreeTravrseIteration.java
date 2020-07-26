package laioffer.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreeTravrseIteration {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        TreeTravrseIteration travrse = new TreeTravrseIteration();

//        System.out.println(travrse.preOrder(root));
//        System.out.println(travrse.inOrder(root));
//        System.out.println(travrse.postOrder(root));
    }

    /**
     * 迭代法进行tree的前序遍历
     * 使用一个stack缓存遍历的数据，栈顶元素就是下一个需要处理的元素
     * <p>
     * 1.将root节点放进stack中
     * 2.遍历stack,将stack中的元素放入list
     * 3.将root.right和root.left按顺序放入stack
     * 4.当stack中没有元素之后，返回list
     * <p>
     * 时间复杂度O(n)
     * <p>
     * 空间复杂度O(height)
     * worst case 缓存最大深度的所有右节点
     */
    public List<Integer> preOrder(TreeNode root) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            res.add(cur.key);
            if (cur.right != null) {
                stack.offerFirst(cur.right);
            }

            if (cur.left != null) {
                stack.offerFirst(cur.left);
            }
        }

        return res;
    }

    /**
     * 迭代法进行tree的中序遍历
     * 使用一个stack缓存tree的元素，另外使用一个helper来看下一个遍历的元素
     * 如果helper != null，说明栈顶元素左子树还未被处理完，将helper元素压栈
     * 如果helper == null，说明栈顶元素没有左子树或已经被处理，则处理栈顶元素
     * 当stack is Empty && helper == null 退出循环
     */
    public List<Integer> inOrder(TreeNode root) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode helper = root;

        while (!stack.isEmpty() || helper != null) {
            if (helper != null) {
                stack.offerFirst(helper);
                helper = helper.left;
            } else {
                TreeNode cur = stack.pollFirst();
                res.add(cur.key);
                helper = cur.right;
            }
        }

        return res;
    }

    /**
     * 迭代法进行tree的后序遍历
     * 使用一个prev来记录上一步访问的节点
     * 1.如果prev == null || prev.left == cur || prev.right == cur
     * 代表上一次访问的节点在cur之上，则cur向下继续走，优先走left，其次为right
     * 2.如果prev == cur.left，代表cur左子树已经访问过了，cur向right走
     * 3.如果prev == cur.right，代表cur的左右子树都已经访问过了，处理cur
     */
    public List<Integer> postOrder(TreeNode root) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode prev = null;

        while(!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            // prev还没动过，或者prev是cur的父节点，此时cur的子树还没有看过
            if (prev == null || prev.left == cur || prev.right == cur) {
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else {
                    res.add(stack.pollFirst().key);
                }
            } else if (prev == cur.left) { // cur的左子树已经处理过了
                if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else {
                    res.add(stack.pollFirst().key);
                }
            } else { // cur的左右子树都已经处理过了
                res.add(stack.pollFirst().key);
            }

            // 更新prev指针到cur
            prev = cur;
        }

        return res;
    }
}
