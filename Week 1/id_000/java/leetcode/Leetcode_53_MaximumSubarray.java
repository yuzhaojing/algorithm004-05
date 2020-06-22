package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 1、两数之和
 */
public class Leetcode_53_MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = method1(nums);
        System.out.println(res);
    }

    /**
     * 动态规划 时间复杂度O(N)
     * DP方程：dp[n] = Max(nums[n], dp[n - 1] + nums[n])
     */
    private static int method1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = nums;
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(0, dp[i - 1]) + nums[i];
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    private static int method2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int sum = Integer.MIN_VALUE;
        int res = nums[0];

        for (int num : nums) {
            if (sum <= 0) {
                sum = num;
            } else {
                sum += num;
            }

            res = Math.max(res, sum);
        }

        return res;
    }
}
