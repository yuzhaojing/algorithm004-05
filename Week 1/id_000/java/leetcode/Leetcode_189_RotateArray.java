package leetcode;

import java.util.Arrays;

/**
 * 189. 旋转数组
 *
 * 1、用当前角标 (i + k) % nums.length算出旋转后的位置，
 *    将数据复制到新的数组中去
 * 2、
 */
public class Leetcode_189_RotateArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int k = 2;
        method2(nums, k);
        System.out.println("nums = " + Arrays.toString(nums));
    }

    private static void method1(int[] nums, int k) {
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[(i + k) % nums.length] = nums[i];
        }

        System.arraycopy(copy, 0, nums, 0, nums.length);
    }

    private static void method2(int[] nums, int k) {
        // 防止k超过数组长度
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
