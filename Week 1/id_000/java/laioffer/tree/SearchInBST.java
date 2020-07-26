package laioffer.tree;

public class SearchInBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(20);

        new SearchInBST().searchRecursion(root, 11);
    }

    /**
     * 递归
     */
    public TreeNode searchRecursion(TreeNode root, int key) {
        // Write your solution here
        if (root == null || root.key == key) {
            return root;
        }

        if (root.key < key) {
            return searchRecursion(root.right, key);
        } else {
            return searchRecursion(root.left, key);
        }
    }

    /**
     * 迭代法
     */
    public TreeNode searchIteration(TreeNode root, int key) {
        // Write your solution here
        if (root == null) {
            return null;
        }

        // 当root != null的时候进行循环迭代
        while (root != null) {
            if (root.key == key) { // 找到了key，跳出循环
                break;
            } else if (root.key < key) { // 没有找到，根据key的大小关系向下走
                root = root.right;
            } else {
                root = root.left;
            }
        }

        // 只有当root == null || root.key == key的时候才会跳出循环
        // 此时直接返回root就可以了
        return root;
    }
}
