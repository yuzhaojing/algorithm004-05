package laioffer.DynamicProgrammingIV;

import java.util.Arrays;

public class LongestAscendingSubsequenceII {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LongestAscendingSubsequenceII().longestBinary(new int[]{28, 4, 8, 14, 14, 12, 7, 14, 28, 24, 9, 30, 28, 29, 26, 3, 17, 18, 5, 29, 18, 8, 30, 32, 13, 29, 6})));
    }

    // input:  int[] array
    // output: int[]
    // Assume: array != null && array.length > 0

    // high level: 使用一维DP，binary scan回头看解答
    // detail level:
    //  1、使用改善最优解序列，计算出最优解序列
    //  2、在每个元素计算出应该放置的位置时，记录一下index，这个就是以这个元素为结尾的最长上升子序列
    //  3、最后通过最优子序列的最后一个元素，找到M序列中的位置，向前遍历找
    //     array[j] < array[i] && M[j] == M[i] - 1

    // time = nlogn + n = O(nlogn)
    // space = 2n = O(n)
    public int[] longestBinary(int[] a) {
        // Write your solution here
        if (a == null || a.length == 0) {
            return new int[0];
        }

        // 以array[i]为结尾的最长上升子序列长度为M[i]
        int[] M = new int[a.length];
        M[0] = 1;

        // 长度为i的最长上升子序列的最优结尾是lowestEnding[i]
        int[] lowestEnding = new int[a.length + 1];
        lowestEnding[1] = a[0];

        int maxLen = 1;
        int endIndex = 0;

        for (int i = 1; i < a.length; i++) {
            int index = smallerLargest(lowestEnding, 1, maxLen, a[i]);
            lowestEnding[index + 1] = a[i];
            M[i] = index + 1;
            if (index == maxLen) {
                maxLen++;
                endIndex = i;
            }
        }

        int[] res = new int[maxLen];
        int cur = maxLen - 1;
        res[cur--] = a[endIndex];

        int prevIndex = endIndex;
        for (int k = endIndex - 1; k >= 0; k--) {
            if (a[k] < a[prevIndex] && M[k] == M[prevIndex] - 1) {
                res[cur--] = a[k];
                prevIndex = k;
            }
        }

        return res;
    }

    private int smallerLargest(int[] array, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        if (array[right] < target) {
            return right;
        }

        if (array[left] < target) {
            return left;
        }

        return 0;
    }

    /**
     * input:  int[] array
     * output: int[]
     * Assume: array != null && array.length > 0
     *
     * high level: 使用一维DP，linear scan回头看解答
     * detail level:
     *  1、使用DP将最大升序子序列的最后节点计算出来
     *  2、通过最后节点，向前遍历，找M[j] = M[i] - 1 && array[j] < array[i]
     *  3、找到M[j] = 1将结果返回
     *
     * time = n^2 + n = O(n^2)
     * space = O(n)
     */
    public int[] longest(int[] a) {
        // Write your solution here
        if (a == null || a.length == 0) {
            return new int[0];
        }

        int[] M = new int[a.length];
        M[0] = 1;

        int maxLen = 1;
        int endIndex = 0;

        for (int i = 0; i < a.length; i++) {
            M[i] = 1;
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i]) {
                    M[i] = Math.max(M[i], M[j] + 1);
                }
            }

            if (M[i] > maxLen) {
                maxLen = M[i];
                endIndex = i;
            }
        }

        int[] res = new int[maxLen];
        int cur = maxLen - 1;
        res[cur--] = a[endIndex];

        int prevIndex = endIndex;
        for (int k = endIndex - 1; k >= 0; k--) {
            if (a[k] < a[prevIndex] && M[k] == M[prevIndex] - 1) {
                res[cur--] = a[k];
                prevIndex = k;
            }
        }

        return res;
    }
}
