package leetcode;

import java.util.Arrays;

/**
 * 1、两数之和
 */
public class Leetcode_31_NextPermutation {

    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        method1(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void method1(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int i = nums.length - 1;
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }

        if (i > 0) {
            int j = nums.length - 1;
            while (j > 0 && nums[j] <= nums[i - 1]) {
                j--;
            }
            swap(nums, i - 1, j);
            reverse(nums, i);
        } else {
            reverse(nums, i);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
