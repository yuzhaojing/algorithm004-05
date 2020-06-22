package leetcode;

import java.util.Arrays;

/**
 * 1、两数之和
 */
public class Leetcode_300_LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int res = method2(nums);
        System.out.println(res);
    }

    /**
     * 动态规划 时间复杂度O(N^2)
     * DP方程：dp[i] = Max(dp[i], dp[j] + 1)
     */
    private static int method1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxLen = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    /**
     * 动态规划 + 二分查找 时间复杂度：O(NlogN)
     * 使用一个数组缓存每个长度的上升子序列的最小尾数，并且角标i存的是长度为i+1的长度上升子序列的最小尾数
     * 然后将每个数在这个有序数组中进行二分查找
     * 如果大于数组末尾元素，则直接在数组末尾加上该元素
     * 否则就找比该元素大的最小值，将其替换
     */
    private static int method2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        int end = 0;

        for (int num : nums) {
            if (num > tails[end]) {
                tails[++end] = num;
            } else {
                int left = 0;
                int right = end;

                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (tails[mid] < num) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                tails[left] = num;
            }
        }

        return end + 1;
    }
}
