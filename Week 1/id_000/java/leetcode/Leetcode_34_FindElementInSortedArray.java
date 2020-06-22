package leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 1、两数之和
 */
public class Leetcode_34_FindElementInSortedArray {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 8, 10};
        System.out.println(Arrays.toString(method1(nums, 8)));
    }

    private static int[] method1(int[] nums, int target) {
        int[] res = {-1, -1};

        if (nums == null || nums.length == 0) return res;

        res[0] = binarySearch(nums, target, true);
        res[1] = binarySearch(nums, target, false);

        return res;
    }

    private static int binarySearch(int[] nums, int target, boolean first) {
        int left = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);

            if (nums[mid] == target) {
                if (first) {
                    right = mid;
                } else left = mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (first) {
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;
        } else {
            if (nums[right] == target) return right;
            if (nums[left] == target) return left;
        }

        return -1;
    }
}
