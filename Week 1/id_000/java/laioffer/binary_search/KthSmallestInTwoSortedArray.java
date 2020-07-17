package laioffer.binary_search;

public class KthSmallestInTwoSortedArray {

    public static void main(String[] args) {
        int[] a = {1, 4, 5, 5, 8, 12, 12, 12};
        int[] b = {2};
        System.out.println(kth(a, b, 20));
    }

    private static int kth(int[] a, int[] b, int k) {
        return kth(a, 0, b, 0, k);
    }

    private static int kth(int[] a, int aleft, int[] b, int bleft, int k) {
        // base case
        if (aleft > a.length - 1) {
            return b[bleft + k - 1];
        }

        if (bleft > b.length - 1) {
            return a[aleft + k - 1];
        }

        if (k == 1) {
            return Math.min(a[aleft], b[bleft]);
        }

        int amid = aleft + k / 2 - 1;
        int bmid = bleft + k / 2 - 1;

        int aval = amid > a.length - 1 ? Integer.MAX_VALUE : a[amid];
        int bval = bmid > b.length - 1 ? Integer.MAX_VALUE : b[bmid];

        if (aval < bval) {
            return kth(a, amid + 1, b, bleft, k - k / 2);
        } else {
            return kth(a, aleft, b, bmid + 1, k - k / 2);
        }
    }
}
