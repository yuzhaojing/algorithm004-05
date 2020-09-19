package laioffer.AfternoonClass.class5_data_structre;

import laioffer.tree.ArrayToTree;
import laioffer.tree.TreeNode;

import java.util.*;

public class BSTTwoNodeSumToTarget {

    public static void main(String[] args) {
        TreeNode root = ArrayToTree.fromArrayToTree(new String[]{"5", "3", "8", "1", "4", "7", "10"});
        System.out.println(Arrays.toString(new BSTTwoNodeSumToTarget().BSTTwoNodeSumToTarget2(root, 17)));
    }

    /**
     * 在BST内找出两个node，他们key的和等于target
     * <p>
     * high level: 有三个方案解答，对比优劣势
     * detail level:
     * 1、使用hashSet，遍历整颗树，将遍历过的node都存在hashSet里，每次遍历新的node都去hashSet内校验一下
     * time = O(n) space = O(n + height)
     * note: 没有用上BST的性质！！
     * 2、使用两个迭代器，一个inOrder遍历，一个与inOrder倒过来遍历。比较两个迭代器返回的值，谁小移谁
     * time = O(n) space = O(height)
     * 3、如果允许修改结构，可以将tree修改成一个升序的double linkedList，然后再用谁小移谁
     * time = O(n) space = O(height)
     */
    public int[] BSTTwoNodeSumToTarget1(TreeNode root, int target) {
        if (root == null) {
            return new int[0];
        }

        Set<Integer> visited = new HashSet<>();
        int[] res = new int[2];
        helper(root, visited, target, res);

        return res;
    }

    private void helper(TreeNode root, Set<Integer> visited, int target, int[] res) {
        if (root != null) {
            if (visited.contains(target - root.key)) {
                res[0] = target - root.key;
                res[1] = root.key;
                return;
            }

            visited.add(root.key);
            helper(root.left, visited, target, res);
            helper(root.right, visited, target, res);
        }
    }

    public int[] BSTTwoNodeSumToTarget2(TreeNode root, int target) {
        if (root == null) {
            return new int[0];
        }

        InOrderIterator inOrderIterator = new InOrderIterator(root);
        InOrderReverseIterator inOrderReverseIterator = new InOrderReverseIterator(root);

        TreeNode left = inOrderIterator.next();
        TreeNode right = inOrderReverseIterator.next();

        int[] res = new int[2];
        while (!left.equals(right)) {
            if (left.key + right.key == target) {
                res[0] = left.key;
                res[1] = right.key;
                return res;
            } else if (left.key + right.key < target) {
                if (inOrderIterator.hasNext()) {
                    left = inOrderIterator.next();
                }
            } else {
                if (inOrderReverseIterator.hasNext()) {
                    right = inOrderReverseIterator.next();
                }
            }
        }

        return new int[0];
    }
}

class InOrderIterator implements Iterator<TreeNode> {
    private Deque<TreeNode> stack;
    private TreeNode root;

    public InOrderIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        this.root = root;
    }


    @Override
    public boolean hasNext() {
        return !stack.isEmpty() || root != null;
    }

    @Override
    public TreeNode next() {
        if (!hasNext()) {
            throw new NoSuchElementException("");
        }

        while (root != null) {
            stack.offerFirst(root);
            root = root.left;
        }

        TreeNode node = stack.pollFirst();
        root = node.right;
        return node;
    }
}

class InOrderReverseIterator implements Iterator<TreeNode> {
    Deque<TreeNode> stack;
    TreeNode root;

    public InOrderReverseIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        this.root = root;
    }


    @Override
    public boolean hasNext() {
        return !stack.isEmpty() || root != null;
    }

    @Override
    public TreeNode next() {
        if (!hasNext()) {
            throw new NoSuchElementException("");
        }

        while (root != null) {
            stack.offerFirst(root);
            root = root.right;
        }

        TreeNode node = stack.pollFirst();
        root = node.left;
        return node;
    }
}