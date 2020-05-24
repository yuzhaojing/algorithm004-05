package divideAndConquer;

import java.util.ArrayList;
import java.util.List;

/**
 * 21.合并两个有序链表
 */
public class Leetcode_169_MajorityElement {

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(method1(nums));
    }

    private static int method1(int[] nums) {
        return majorityElement(nums, 0, nums.length - 1);
    }

    private static int majorityElement(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int mid = left + (right - left) / 2;

        int leftElement = majorityElement(nums, left, mid);
        int rightElement = majorityElement(nums, mid + 1, right);

        if (leftElement == rightElement) return leftElement;

        int leftElementCount = countRange(nums, leftElement, left, right);
        int rightElementCount = countRange(nums, rightElement, left, right);

        return leftElementCount > rightElementCount ? leftElement : rightElement;
    }

    private static int countRange(int[] nums, int target, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == target) count++;
        }
        return count;
    }
}
