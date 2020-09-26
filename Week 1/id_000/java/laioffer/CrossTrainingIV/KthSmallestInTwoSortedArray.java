package laioffer.CrossTrainingIV;

public class KthSmallestInTwoSortedArray {

    public static void main(String[] args) {
        int[] a = {1, 4, 5, 5, 8, 12, 12, 12};
        int[] b = {2};

        System.out.println(new KthSmallestInTwoSortedArray().kth(a, b, 9));
    }

    /**
     * input:  int[] a
     *         int[] b
     *         int k
     * output: int
     * Assume: a != null && b != null && a.length + b.length >= k
     *
     * high level: 使用binary search解答
     * detail level:
     *  1、对a、b两个数组各取前k/2个元素，比较值的大小。小的那边的第k/2个元素必然不是第k小的，因为可能比他小的都不足k个元素
     *  2、舍弃掉小的那k/2个元素之后，继续对两个数组剩下的元素各取k/4个元素，以此类推
     *  3、如果任意一个数组元素不足，直接舍弃掉另一个数组的元素。因为一个数组连一半都凑不齐，另一个数组的一般必然是前k小的
     *
     * time = 2 * logk = O(logk)
     * space = O(logk)
     */
    public int kth(int[] a, int[] b, int k) {
        // Write your solution here
        if (a == null && b == null) {
            return Integer.MIN_VALUE;
        }

        if (a == null) {
            if (b.length >= k) {
                return b[k - 1];
            } else {
                return Integer.MIN_VALUE;
            }
        } else if (b == null) {
            if (a.length >= k) {
                return a[k - 1];
            } else {
                return Integer.MIN_VALUE;
            }
        } else if (a.length + b.length < k) {
            return Integer.MIN_VALUE;
        }

        return helper(a, 0, b, 0, k);
    }

    private int helper(int[] a, int aleft, int[] b, int bleft, int k) {
        // 下面进行递归的时候，amid + 1和bmid + 1有可能会越界
        // 此时表示对应数组的元素已经被取完，直接返回另一个数组的元素即可
        if (aleft >= a.length) {
            return b[bleft + k - 1];
        }

        if (bleft >= b.length) {
            return a[aleft + k - 1];
        }

        if (k == 1) {
            return Math.min(a[aleft], b[bleft]);
        }

        int amid = aleft + k / 2 - 1;
        int bmid = bleft + k / 2 - 1;

        int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
        int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];

        if (aval < bval) {
            return helper(a, amid + 1, b, bleft, k - k / 2);
        } else {
            return helper(a, aleft, b, bmid + 1, k - k / 2);
        }
    }
}
