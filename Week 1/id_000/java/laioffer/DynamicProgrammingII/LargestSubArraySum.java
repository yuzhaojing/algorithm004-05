package laioffer.DynamicProgrammingII;

import java.util.Arrays;

public class LargestSubArraySum {

    public static void main(String[] args) {
        int[] array = {2, 3, 4, 0};
        System.out.println(new LargestSubArraySum().largestSum(array));
    }

    /**
     * 假设：array != null && array.length > 0
     * 如果不符合假设，那么需要和面试官讨论，那么该数组的最大和应该是0，左右边界不存在，暂定返回-1
     *
     * high level: 使用一维DP进行解答
     * mid level: linear scan回头看，每次回头看前一位的最大和
     *  1、M[i]表示从[0, i]并且以array[i]作为最后一个元素的最大子数组和
     *  2、base case: M[0] = array[0]
     *  3、induction rule: M[i] = M[i - 1] < 0 ? array[i] : M[i - 1] + array[i]
     *
     * time = O(n)
     * space = O(1) 由于回头看的个数为常数，可以用变量存储
     */
    public int largestSumBetter(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 0;
        }

        int prev = array[0];
        int globalMax = prev;

        for (int i = 1; i < array.length; i++) {
            prev = Math.max(array[i], prev + array[i]);
            globalMax = Math.max(globalMax, prev);
        }

        return globalMax;
    }

    /**
     * base case: M[0] = array[0]
     * induction rule: M[i] = if (M[i - 1] < 0)     array[i]
     *                        else                  M[i - 1] + array[i]
     *
     * <p>
     * time = O(n)
     * <p>
     * space = O(n)
     */
    public int largestSum(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 0;
        }

        int[] M = new int[array.length];
        M[0] = array[0];

        int globalMax = Integer.MIN_VALUE;

        for (int i = 1; i < array.length; i++) {
            if(M[i - 1] < 0) {
                M[i] = array[i];
            } else {
                M[i] = M[i - 1] + array[i];
            }
            globalMax = Math.max(globalMax, M[i]);
        }

        return globalMax;
    }
}
