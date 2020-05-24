package array;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 */
public class Leetcode_88_MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        method3(nums1, 3, nums2, 3);
        System.out.println("nums1 = " + Arrays.toString(nums1));
    }

    private static void method1(int[] nums1, int m, int[] nums2, int n) {
        int[] tempArr = new int[m];
        int index = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (nums1[i] <= nums2[j]) {
                    tempArr[index] = nums1[i];
                    index++;
                } else {
                    tempArr[index] = nums2[j];
                    index++;
                }
            }
        }
        System.arraycopy(tempArr, 0, nums1, 0, tempArr.length);
    }

    private static void method2(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    private static void method3(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        int index = m + n - 1;

        while(index1 >= 0 && index2 >= 0) {
            nums1[index--] = nums1[index1] > nums2[index2] ? nums1[index1--] : nums2[index2--];
        }

        System.arraycopy(nums2, 0, nums1, 0, index2 + 1);
    }

}
