package laioffer.tree;

public class InsertInBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(20);

        root = new InsertInBST().insertIteration2(root, 13);
    }

    /**
     * 递归
     */
    public TreeNode insertRecursion(TreeNode root, int key) {
        // Write your solution here
        if (root == null) {
            return new TreeNode(key);
        }

        if (root.key < key) {
            root.right = insertRecursion(root.right, key);
        } else if (root.key > key) {
            root.left = insertRecursion(root.left, key);
        }

        return root;
    }

    /**
     * 迭代方法1(往回看一眼)
     * 保留prev节点，当cur指针找到null的时候，prev和cur节点连接
     *
     * 时间复杂度O(height)
     * worst case为插入的元素插入在树的最底层
     *
     * 空间复杂度O(1)
     *
     */
    public TreeNode insertIteration1(TreeNode root, int key) {
        // Write your solution here
        TreeNode target = new TreeNode(key);
        if (root == null) {
            return target;
        }

        TreeNode prev = null;
        TreeNode cur = root;

        // cur会向下移动，并且不知道是否为null
        // 可以使用cur != null为循环条件

        // 没有使用cur.key != key作为循环条件
        // 是因为如果第一个元素与key相等的话，prev为null
        // 下面或者上面需要额外的逻辑处理
        while (cur != null) {
            // prev先移动一步，和cur重合
            prev = cur;
            if (cur.key == key) {
                return root;
            } else if (cur.key < key) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }

        // 找到需要插入的key的位置了，判断应该插在prev的具体位置
        // prev.key一定不等于key，这种情况在while循环内就会直接返回
        if (prev.key < key) {
            prev.right = target;
        } else {
            prev.left = target;
        }

        return root;
    }

    /**
     * 迭代方法1(往下看一眼)
     * 每次迭代的时候，看一下下一个移动点是否是null
     * 如果为null，直接将需要插入的node插入到对应位置返回
     */
    public TreeNode insertIteration2(TreeNode root, int key) {
        TreeNode target = new TreeNode(key);
        if (root == null) {
            return target;
        }

        TreeNode cur = root;

        // cur每次向下移动之前，都会探测一遍，所以cur必然不为null
        // 将cur.key != key提取出来作为条件
        while (cur.key != key) {
            if (cur.key < key) {
                if (cur.right == null) {
                    cur.right = target;
                    break;
                } else {
                    cur = cur.right;
                }
            } else {
                if (cur.left == null) {
                    cur.left = target;
                    break;
                } else {
                    cur = cur.left;
                }
            }
        }

        return root;
    }


}
