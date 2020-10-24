package laioffer.tree;

public class DeleteInBST {

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(10);
//        root.left = new TreeNode(5);
//        root.left.left = new TreeNode(2);
//        root.left.right = new TreeNode(7);
//        root.right = new TreeNode(15);
//        root.right.left = new TreeNode(11);
//        root.right.right = new TreeNode(20);

        TreeNode root = ArrayToTree.fromArrayToTree(new String[] {"6","3","12","2","4","8","16","1","#","#","5","7","9","14","18"});

        root = new DeleteInBST().deleteTree(root, 6);
        System.out.println(root.key);
    }

    /**
     * 在一颗二叉搜索树中删除一个节点，并且在删除后依然保留二叉搜索树的属性
     *
     * 1.当被删除节点是叶子节点的时候，只需要将该节点直接删除
     * 2.当被删除节点只有左子树时，直接将左子树接到被删除节点的位置
     * 3.当被删除节点只有右子树时，直接将右子树接到被删除节点的位置
     * 4.当被删除节点既有左子树，又有右子树时，可以找左子树最大值或者右子树最小值替代（以右子树为例）
     *   1、如果右子树的根没有左节点，则右子树的根为最小值
     *      将右子树的根接到被删除节点的位置，然后左子树接到右子树的根的左节点上
     *   2、如果右子树的根有左节点，则顺着左节点一直找到最小值，将最小值节点被删除节点的位置
     *      然后将最小值的右节点接到最小值父节点的左节点上
     *
     * 时间复杂度O(height)
     * worst case为被删除节点在树的最底层 or 被删除节点左子树最大值或者右子树最小值在树的最底层
     *
     * 空间复杂度O(height) ？如果改为迭代是否是O(1)
     * worst case为被删除节点在树的最底层
     */
//    public TreeNode deleteTree(TreeNode root, int key) {
//        // Write your solution here
//        if (root == null) {
//            return null;
//        }
//
//        // 当前节点不是要删除的节点，递归寻找要删除的节点
//        if (root.key < key) {
//            root.right = deleteTree(root.right, key);
//            return root;
//        } else if (root.key > key) {
//            root.left = deleteTree(root.left, key);
//            return root;
//        }
//
//        // 当前节点是要删除的节点
//        // 如果左子树为null，返回右子树
//        // 如果右子树为null，返回左子树
//        if (root.left == null) {
//            return root.right;
//        } else if (root.right == null) {
//            return root.left;
//        }
//
//        // 左右子树都不为null，查找右子树中最小值
//        // 如果右子树没有左子树，则右子树的根节点为最小值，将左子树挂在右子树的左节点，并返回右子树
//        if (root.right.left == null) {
//            root.right.left = root.left;
//            return root.right;
//        }
//
//        // 删除右子树中最小的node，并将其接到root的位置上
//        TreeNode node = deleteSmallest(root.right);
//        node.left = root.left;
//        node.right = root.right;
//        return node;
//    }
//
//    private TreeNode deleteSmallest(TreeNode root) {
//        if (root == null) {
//            return null;
//        }
//
//        TreeNode prev = null;
//        while (root.left != null) {
//            prev = root;
//            root = root.left;
//        }
//        prev.left = root.right;
//        root.right = null;
//        return root;
//    }


    public TreeNode deleteTree(TreeNode root, int key) {
        // Write your solution here
        if (root == null) {
            return null;
        }

        if (root.key < key) {
            root.right = deleteTree(root.right, key);
            return root;
        } else if (root.key > key) {
            root.left = deleteTree(root.left, key);
            return root;
        }

        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }

        if (root.right.left == null) {
            root.right.left = root.left;
            return root.right;
        }

        TreeNode newRoot = findNode(root.right);
        newRoot.left = root.left;
        newRoot.right = root.right;

        return newRoot;
    }

    private TreeNode findNode(TreeNode root) {
        TreeNode prev = null;
        while (root.left != null) {
            prev = root;
            root = root.left;
        }

        prev.right = root.right;
        return root;
    }

}
