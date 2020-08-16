package laioffer.DynamicProgrammingII;

import java.util.Arrays;

public class LargestSubArraySum {

    public static void main(String[] args) {
        int[] array = {2, 3, 4, 0};
        System.out.println(new LargestSubArraySum().largestSum(array));
    }

    /**
     * 由于dp数组中每次只需要和前一位进行比较
     * 所以可以使用一个变量储存前一位的值
     *
     * time = O(n)
     *
     * space = O(1)
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
