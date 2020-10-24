package laioffer.recursionI_and_sorting_algorithms;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 8};
        new MergeSort().mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 时间复杂度O(nlogn)
     * 分析思路: 由于每次都将数组二分，所以递归树有logn层，每次merge时间复杂度为O(n)
     *
     * array
     *
     * split阶段: 每次split为O(1),split次数随着层数增加而增加,一共logn层
     *            Time = 1 + 2 + 4 + ... + n / 2 = n -> O(n)
     *            等比数列，最后一项比之前总和都大，所以可以看成 n / 2 + n / 2 = n
     * merge阶段: 每层merge为O(n)，因为总共需要merge n个元素，一共logn层
     *            Time = n * logn = O(nlogn)
     *
     * total: Time = O(n + nlogn) = O(nlogn)
     *
     *
     * linkedlist
     *
     * split阶段: 每层split为O(n),一共logn层
     *            第一层：一个数组，n个元素，split O(n)
     *            第二层：两个数组，每个n/2个元素，split O(n/2 + n/2)
     *            第三层：四个数组，每个n/4个元素，split O(n/4 + n/4 + n/4 + n/4)
     *            Time = n * logn = O(nlogn)
     *            等比数列，最后一项比之前都大，所以可以看成 n / 2 + n / 2 = n
     * merge阶段: 每层merge为O(n)，因为总共需要merge n个元素，一共logn层
     *            Time = n * logn = O(nlogn)
     *
     * total: Time = O(nlogn + nlogn) = O(nlogn)
     *
     * 空间复杂度O(logn)
     * 分析思路:
     * 分治阶段: 每层仅存储left，mid，right三个变量，空间复杂度为O(1)，递归树有logn层 O(logn)
     * 合并阶段: 只需要额外存储一个dummyNode，空间复杂度为O(1)，递归树有logn层 O(logn)
     * total: Space = O(n + logn) = O(n)
     */
    public int[] mergeSort(int[] array) {
        // Write your solution here
        if (array == null || array.length <= 1) {
            return array;
        }

        mergeSort(array, new int[array.length], 0, array.length - 1);
        return array;
    }

    private void mergeSort(int[] array, int[] helper, int left, int right) {
        // Base Case
        if (left >= right) {
            return;
        }

        // recursion rule
        int mid = left + (right - left) / 2;
        mergeSort(array, helper, left, mid);
        mergeSort(array, helper, mid + 1, right);
        merge(array, helper, left, mid, right);
    }

    private void merge(int[] array, int[] helper, int left, int mid, int right) {
        int len = right - left + 1;

        int leftIndex = left;
        int rightIndex = mid + 1;
        int helperIndex = 0;

        while (leftIndex <= mid && rightIndex <= right) {
            helper[helperIndex++] = array[leftIndex] < array[rightIndex] ? array[leftIndex++] : array[rightIndex++];
        }

        while (leftIndex <= mid) {
            helper[helperIndex++] = array[leftIndex++];
        }

        while (rightIndex <= right) {
            helper[helperIndex++] = array[rightIndex++];
        }

        for(int i = 0; i < len; i++) {
            array[left++] = helper[i];
        }
    }
}
