package laioffer.AfternoonClass.class3_binarysearch;

public class KthSmallestInTwoSortedArray {

    public static void main(String[] args) {
        int[] one = {1, 3, 5, 7};
        int[] two = {2, 4, 6, 8};

        System.out.println(new KthSmallestInTwoSortedArray().KthSmallestInTwoSortedArray(one, two, 5));
    }

    /**
     * 1、找到两个排序数组中，第k小的数
     *
     * input:  int[] one
     *         int[] two
     * output: int
     * Assume: one != null && two != null
     *
     * high level:
     * detail level:
     *   1、两个数组同时取第k/2个数，然后比较哪个小，小的那个以及它前面的都必然是前k个数
     *      都可以删除，然后继续比较
     *   2、证明：如果较小的那个数是第k个或者更大的数，那么另一个数组的第k/2个数必然大于第k个数
     *           则前k个数不足k个，不成立
     *
     * time = O(logk)
     * space = O(1)
     */
    public int KthSmallestInTwoSortedArray(int[] one, int[] two, int k) {
        if (one == null) {
            return two[k - 1];
        }
        if (two == null) {
            return one[k - 1];
        }
        return helper(one, 0, two, 0, k);
    }

    private int helper(int[] one, int oneLeft, int[] two, int twoLeft, int k) {
        if (oneLeft >= one.length) {
            return two[twoLeft + k - 1];
        }

        if (twoLeft >= two.length) {
            return one[oneLeft + k - 1];
        }

        if (k == 1) {
            return Math.min(one[oneLeft], two[twoLeft]);
        }

        int oneMid = oneLeft + k / 2 - 1;
        int twoMid = twoLeft + k / 2 - 1;

        int oneVal = oneMid >= one.length ? Integer.MAX_VALUE : one[oneMid];
        int twoVal = twoMid >= two.length ? Integer.MAX_VALUE : two[twoMid];

        if (oneVal < twoVal) {
            return helper(one, oneMid + 1, two, twoLeft, k - k / 2);
        } else {
            return helper(one, oneLeft, two, twoMid + 1, k - k / 2);
        }
    }


    /**
     * follow up
     * 2、找到n个排序数组中，第k小的数
     */
}
