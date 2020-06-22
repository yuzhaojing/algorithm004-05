package leetcode;

import java.util.Arrays;

/**
 * 1、两数之和
 */
public class Leetcode_75_SortColors {

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        method1(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void method1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        for (int i = 0; i <= right; i++) {
            if (nums[i] == 0) swap(nums, left++, i);
            if (nums[i] == 2) swap(nums, right--, i--);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
