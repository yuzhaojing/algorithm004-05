package laioffer.recursionI_and_sorting_algorithms;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {5, 1, 1, 2, 0, 0};
        new QuickSort().quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 时间复杂度
     * avg      O(nlogn)
     * worst    O(n^2)
     * partition函数选出pivot，然后将比pivot小的数放在左边，比pivot大的数放在右边
     * 每个元素都需要扫一遍，时间复杂度O(n)
     * 平均情况为递归logn层
     * 最坏情况为pivot每次都是剩余元素中最大或者最小的，那么一共递归n层
     */
    public int[] quickSort(int[] array) {
        // Write your solution here
        if (array == null || array.length <= 1) {
            return array;
        }

        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int left, int right) {
        // Base Case
        if (left >= right) {
            return;
        }

        //revursion rule
        int pivotIndex = partition(array, left, right);
        // pivot不需要再排序了
        quickSort(array, left, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, right);

        int leftBound = left;
        int rightBound = right - 1;

        while (leftBound <= rightBound) {
            if (array[leftBound] < pivot) {
                leftBound++;
            } else {
                swap(array, leftBound, rightBound--);
            }
        }

        swap(array, leftBound, right);
        return leftBound;
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
